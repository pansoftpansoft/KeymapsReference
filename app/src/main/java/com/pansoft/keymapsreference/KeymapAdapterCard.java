package com.pansoft.keymapsreference;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KeymapAdapterCard extends RecyclerView.Adapter<KeymapAdapterCard.KeymapViewHolder> {
    int[] id;
    String[] name;
    String[] section;
    String[] descriptionEnKeymap;
    String[] codeWinKeymap;

    int ListSize;
    public KeymapAdapterCard(int[] id_data, String[] name_data, String[] section_data, String[] descriptionEnKeymap_data, String[] codeWinKeymap_data) {

        id =  id_data;
        name = name_data;
        section = section_data;
        descriptionEnKeymap = descriptionEnKeymap_data;
        codeWinKeymap = codeWinKeymap_data;
        ListSize= id.length;
        Log.d("mLog", "id = " + ListSize + " id = " + id[0]);
        Log.d("mLog", "name = " + name.length + " id = " + name[0]);
        Log.d("mLog", "section = " + section.length + " id = " + section[0]);
    }

    @NonNull
    @Override
    public KeymapAdapterCard.KeymapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("mLog", "position = " + 100);
        Context context = parent.getContext();
        int layoutItem = R.layout.item_reference_card;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutItem, parent, false);
        KeymapViewHolder viewHolder = new KeymapViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KeymapAdapterCard.KeymapViewHolder holder, int position) {
        Log.d("mLog", "position = " + position);

        holder.bind(id[position], name[position],section[position],descriptionEnKeymap[position],codeWinKeymap[position]);
    }

    @Override
    public int getItemCount() {
        return ListSize;
    }

    public class KeymapViewHolder extends RecyclerView.ViewHolder {
        TextView textKeymapCode;
        TextView textActionEn;
        TextView textActionTranslation;


        public KeymapViewHolder(@NonNull View itemView) {
            super(itemView);
            textKeymapCode = itemView.findViewById(R.id.textKeymapCode);
            textActionEn = itemView.findViewById(R.id.textActionEn);
            textActionTranslation = itemView.findViewById(R.id.textActionTranslation);
        }

        void bind(int id_data, String name_data, String section_data, String descriptionEn_data, String codeWin_data) {
            Log.d("mLog", "id = " + id_data + " - " + codeWin_data + " - " + name_data  + " - " + section_data);
            textActionTranslation.setText(descriptionEn_data);
            textKeymapCode.setText(String.valueOf(codeWin_data));
            textActionEn.setText(name_data);
        }
    }
}
