package com.pansoft.keymapsreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ViewReference extends AppCompatActivity implements View.OnClickListener {
    private KeymapAdapter keymapAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reference);

        Intent intent = getIntent();

        int[] ides = intent.getExtras().getIntArray("id");
        String[] name = intent.getExtras().getStringArray("name");
        String[] section = intent.getExtras().getStringArray("section");
        String[] descriptionEnKeymap = intent.getExtras().getStringArray("descriptionEn");
        String[] codeWinKeymap = intent.getExtras().getStringArray("codeWin");

//        int[] ides = {1};
//        String[] name = {"2"};
//        String[] section = {"3"};

        int nnn= name.length;
        Log.d("mLog", "____id.length = " + nnn);


        int eee = ides.length;

        Log.d("mLog", "____id.length = " + eee);
        Log.d("mLog", "____id.[0] = " + ides[0]);


        recyclerView = findViewById(R.id.recyclerViewKeymap);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        keymapAdapter = new KeymapAdapter(ides, name, section,descriptionEnKeymap,codeWinKeymap);
        recyclerView.setAdapter(keymapAdapter);


    }

    @Override
    public void onClick(View view) {

    }
}
