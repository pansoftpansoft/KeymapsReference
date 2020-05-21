package com.pansoft.keymapsreference;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 52;
    public static final String DATABASE_NAME = "KeyMapReference";

    public static final String TABLE_KEYMAP = "keymap";
    public static final String KEY_ID = "_id_keymap";
    public static final String KEY_ID_SECTION = "id_section";
    public static final String KEY_ID_CONTEXT_KEYMAP = "id_context";
    public static final String KEY_CODE_WIN = "code_win";
    public static final String KEY_CODE_APPLE = "code_apple";
    public static final String KEY_NAME_EN = "name_en";
    public static final String KEY_NAME_TR = "name_tr";
    public static final String KEY_DESCRIPTION_TR = "description_tr";
    public static final String KEY_DESCRIPTION_EN = "description_en";

    String[] TABLE_FIELD_V1 = {"_id_keymap integer primary key, ",
            "id_section integer, ",
            "id_context integer, ",
            "code_win text, ",
            "code_apple text, ",
            "name_en text, ",
            "name_tr text, ",
            "description_tr text, ",
            "description_en text "};

    String JsonString = "[[{\"number11\":\"value11\", \"number21\":\"value21\", \"number31\":\"value31\" }]," +
            "[{\"number12\":\"value12\", \"number22\":\"value22\", \"number32\":\"value32\" }], " +
            "[{\"number13\":\"value13\", \"number23\":\"value23\", \"number33\":\"value33\" }]," +
            "[{\"number14\":\"value14\", \"number24\":\"value24\", \"number34\":\"value34\" }]]";

    public static final String TABLE_SECTION_USE = "section_use";
    public static final String KEY_ID_SECTION_USE = "_id_section";
    public static final String KEY_SECTION_NAME_EN = "section_name_en";
    public static final String KEY_SECTION_NAME_TR = "section_name_tr";

    public static final String TABLE_CONTEXT_USE = "context";
    public static final String KEY_ID_CONTEXT = "_id_context";
    public static final String KEY_CONTEXT_NAME_EN = "context_name_en";
    public static final String KEY_CONTEXT_NAME_TR = "context_name_tr";

    public String packageName;
    public Resources resources;
    public Context contextSave;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        packageName = context.getPackageName();
        resources = context.getResources();
        contextSave = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Создаем таблицу Keymap
        createTable(sqLiteDatabase, TABLE_KEYMAP, TABLE_FIELD_V1);
        // Получаем строку Json из файла ресурсов
        try {
            JsonString = getStringFromRawJsonFile("key_map");
            Log.d("mLog", "JsonString = " + JsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Заполняем заплняем таблицу данными из  JsonString
        try {
            addDataInTable(sqLiteDatabase, TABLE_KEYMAP, JsonString);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
//        ContentValues values = new ContentValues();
//        sqLiteDatabase.execSQL("create table " + TABLE_CONTACT + "(" + KEY_ID
//                + " integer primary key, " + KEY_ID_SECTION + " text, " + KEY_NAME + " text " + ")");
//        values.put(KEY_ID, 1);
//        values.put(KEY_ID_SECTION, "Base");
//        values.put(KEY_NAME, "Основная");
//        sqLiteDatabase.insert(TABLE_CONTACT, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_KEYMAP);
        onCreate(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase sqLiteDatabase, String tableName, String[] tableFields) {
        /*Процедура по ссзданию таблиц*/
        String sql_template;
        sql_template = "create table " + tableName + "(";
        for (String fields : tableFields) {
            sql_template = sql_template + fields;
        }
        sql_template = sql_template + ");";
        sqLiteDatabase.execSQL(sql_template);
    }


    public String getStringFromRawJsonFile(String path) throws IOException {
        // инициализируем поток вывода из файлу

        int resourceID = resources.getIdentifier(
                "key_map",
                "raw",
                packageName
        );

        Log.d("mLog", "getApplicationContext().getPackageName() = " + packageName);
        Log.d("mLog", "resourceID = " + String.valueOf(resourceID));

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonString = null;
//            inputStream = getResources().openRawResource(R.raw.key_map);
            inputStream = resources.openRawResource(resourceID);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

    void addDataInTable(SQLiteDatabase sqLiteDatabase, String tableName, String tableDatesJson) throws JSONException, IOException {
        /*
        Добавление данных в таблицу
        Пример данных
            String JsonString = "[[{\"number11\":\"value11\", \"number21\":\"value21\", \"number31\":\"value31\" }," +
            "{\"number12\":\"value12\", \"number22\":\"value22\", \"number32\":\"value32\" }, " +
            "{\"number13\":\"value13\", \"number23\":\"value23\", \"number33\":\"value33\" }," +
            "{\"number14\":\"value14\", \"number24\":\"value24\", \"number34\":\"value34\" }]";
        */

        ContentValues values = new ContentValues();
        JSONArray ar = new JSONArray(tableDatesJson);
        Iterator<String> iterator;
        for (int i = 0; i < ar.length(); i++) {
            //JSONArray o = new JSONArray(String.valueOf(ar.getJSONArray(i)));
            //for (int y = 0; y < o.length(); y++) {
            JSONObject o2 = new JSONObject(ar.getJSONObject(i).toString());
            iterator = o2.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                try {
                    Object value = o2.get(key);
                    values.put(key, value.toString());
                } catch (JSONException e) {
                    Log.d("mLog", e.toString());
                }
            }
            sqLiteDatabase.insert(tableName, null, values);
        }
    }
}
