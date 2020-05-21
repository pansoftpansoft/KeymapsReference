package com.pansoft.keymapsreference;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewReferenceCard extends AppCompatActivity implements View.OnClickListener {
    private KeymapAdapterCard keymapAdapterCard;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reference_card);

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


        recyclerView = findViewById(R.id.recyclerViewKeymapCard);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        keymapAdapterCard = new KeymapAdapterCard(ides, name, section,descriptionEnKeymap,codeWinKeymap);
        recyclerView.setAdapter(keymapAdapterCard);


    }

    @Override
    public void onClick(View view) {

    }
}
