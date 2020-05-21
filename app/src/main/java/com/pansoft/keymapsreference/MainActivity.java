package com.pansoft.keymapsreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonOpenViewReference;
    Button button_card;
    DBHelper dbHelper;
    public String stringJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        buttonOpenViewReference = findViewById(R.id.buttonOpenViewReference);
        buttonOpenViewReference.setOnClickListener(this);

        button_card = findViewById(R.id.button_card);
        button_card.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Intent intent;
        String[] codeWinKeymap;
        String[] nameKeymap;
        String[] descriptionEnKeymap;
        String[] sectionKeymap;
        int[] idKeymap;

        List<String> list;
        Cursor cursor;
        int lengthDim;
        switch (view.getId()) {
            case R.id.buttonOpenViewReference:
                // Create List
                cursor = database.query(DBHelper.TABLE_KEYMAP, null, null, null, null, null, null);
                idKeymap = new int[cursor.getCount()];
                 codeWinKeymap = new String[cursor.getCount()];
                 nameKeymap = new String[cursor.getCount()];
                 descriptionEnKeymap = new String[cursor.getCount()];
                 sectionKeymap = new String[cursor.getCount()];

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int codeWinIndex = cursor.getColumnIndex(DBHelper.KEY_CODE_WIN);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME_EN);
                    int descriptionEnIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION_EN);
                    int sectionIndex = cursor.getColumnIndex(DBHelper.KEY_ID_SECTION);
                    do {
                        int rr = cursor.getPosition();
                        Log.d("mLog", "cursor.getPosition() = " + rr);
                        idKeymap[cursor.getPosition()] = cursor.getInt(idIndex);
                        codeWinKeymap[cursor.getPosition()] = cursor.getString(codeWinIndex);
                        nameKeymap[cursor.getPosition()] = cursor.getString(nameIndex);
                        descriptionEnKeymap[cursor.getPosition()] = cursor.getString(descriptionEnIndex);
                        sectionKeymap[cursor.getPosition()] = cursor.getString(sectionIndex);

                        Log.d("mLog", "Position" + cursor.getPosition() + " id = " + cursor.getInt(idIndex) +
                                " name = " + cursor.getString(nameIndex) +
                                " codeWin = " + cursor.getString(codeWinIndex) +
                                " description = " + cursor.getString(descriptionEnIndex) +
                                " section = " + cursor.getString(sectionIndex)
                        );

                    } while (cursor.moveToNext());
                } else {
                    Log.d("mLog", "0 rows");
                }
                cursor.close();
                //Log.d("mLog", "id.length = " + idKeymap.length + " idKeymap = " + idKeymap[0]);
                intent = new Intent(this, ViewReference.class);
                intent.putExtra("id", idKeymap);
                intent.putExtra("name", nameKeymap);
                intent.putExtra("section", sectionKeymap);
                intent.putExtra("descriptionEn", descriptionEnKeymap);
                intent.putExtra("codeWin", codeWinKeymap);
                startActivity(intent);
                break;
            case R.id.button_card:
                cursor = database.query(DBHelper.TABLE_KEYMAP, null, null, null, null, null, null);
                 idKeymap = new int[cursor.getCount()];
                 codeWinKeymap = new String[cursor.getCount()];
                 nameKeymap = new String[cursor.getCount()];
                 descriptionEnKeymap = new String[cursor.getCount()];
                 sectionKeymap = new String[cursor.getCount()];

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int codeWinIndex = cursor.getColumnIndex(DBHelper.KEY_CODE_WIN);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME_EN);
                    int descriptionEnIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION_EN);
                    int sectionIndex = cursor.getColumnIndex(DBHelper.KEY_ID_SECTION);
                    do {
                        int rr = cursor.getPosition();
                        Log.d("mLog", "cursor.getPosition() = " + rr);
                        idKeymap[cursor.getPosition()] = cursor.getInt(idIndex);
                        codeWinKeymap[cursor.getPosition()] = cursor.getString(codeWinIndex);
                        nameKeymap[cursor.getPosition()] = cursor.getString(nameIndex);
                        descriptionEnKeymap[cursor.getPosition()] = cursor.getString(descriptionEnIndex);
                        sectionKeymap[cursor.getPosition()] = cursor.getString(sectionIndex);

                        Log.d("mLog", "Position" + cursor.getPosition() + " id = " + cursor.getInt(idIndex) +
                                " name = " + cursor.getString(nameIndex) +
                                " codeWin = " + cursor.getString(codeWinIndex) +
                                " description = " + cursor.getString(descriptionEnIndex) +
                                " section = " + cursor.getString(sectionIndex)
                        );

                    } while (cursor.moveToNext());
                } else {
                    Log.d("mLog", "0 rows");
                }
                cursor.close();
                //Log.d("mLog", "id.length = " + idKeymap.length + " idKeymap = " + idKeymap[0]);
                intent = new Intent(this, ViewReferenceCard.class);
                intent.putExtra("id", idKeymap);
                intent.putExtra("name", nameKeymap);
                intent.putExtra("section", sectionKeymap);
                intent.putExtra("descriptionEn", descriptionEnKeymap);
                intent.putExtra("codeWin", codeWinKeymap);
                Log.d("mLog", "Start ViewReferenceCard");

                startActivity(intent);

                break;
        }

    }


}
