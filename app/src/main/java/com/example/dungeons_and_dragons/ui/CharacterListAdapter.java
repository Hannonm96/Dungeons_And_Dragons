package com.example.dungeons_and_dragons.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dungeons_and_dragons.R;
import com.example.dungeons_and_dragons.model.Campaign;
import com.example.dungeons_and_dragons.model.Character;
import com.example.dungeons_and_dragons.model.User;

import java.util.ArrayList;
import java.util.List;

public class CharacterListAdapter extends ListAdapter<Character, CharacterListAdapter.CharacterViewHolder> {
    private OnItemClickListener listener;

    public CharacterListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Character> DIFF_CALLBACK = new DiffUtil.ItemCallback<Character>() {
        // check if the items have the same id
        @Override
        public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.getCharacterId() == newItem.getCharacterId();
        }
        // check to see if the names are the same
        @Override
        public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getLevel() == (newItem.getLevel()) &&
                    oldItem.getStrength() == (newItem.getStrength()) &&
                    oldItem.getConstitution() == (newItem.getConstitution()) &&
                    oldItem.getDexterity() == (newItem.getDexterity()) &&
                    oldItem.getIntelligence() == (newItem.getIntelligence()) &&
                    oldItem.getWisdom() == (newItem.getWisdom()) &&
                    oldItem.getCharisma() == (newItem.getCharisma());
        }
    };

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_item, viewGroup, false);

        return new CharacterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder charViewHolder, int position) {
        // set the text from db
        Character current = getItem(position);
        charViewHolder.characterName.setText(current.getName());
        charViewHolder.characterLevel.setText(String.valueOf(current.getLevel()));
        charViewHolder.characterStr.setText(String.valueOf(current.getStrength()));
        charViewHolder.characterConst.setText(String.valueOf(current.getConstitution()));
        charViewHolder.characterDex.setText(String.valueOf(current.getDexterity()));
        charViewHolder.characterInt.setText(String.valueOf(current.getIntelligence()));
        charViewHolder.characterWis.setText(String.valueOf(current.getWisdom()));
        charViewHolder.characterChar.setText(String.valueOf(current.getCharisma()));

    }
    // get campaign at position
    public Character getCharacterAt(int pos) {
        return getItem(pos);
    }


    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        private TextView characterName;
        private TextView characterLevel;
        private TextView characterStr;
        private TextView characterConst;
        private TextView characterDex;
        private TextView characterInt;
        private TextView characterWis;
        private TextView characterChar;


        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.character_name);
            characterLevel = itemView.findViewById(R.id.character_level_text);
            characterStr = itemView.findViewById(R.id.character_str_text);
            characterConst = itemView.findViewById(R.id.character_const_text);
            characterDex = itemView.findViewById(R.id.character_dex_text);
            characterInt = itemView.findViewById(R.id.character_int_text);
            characterWis = itemView.findViewById(R.id.character_wis_text);
            characterChar = itemView.findViewById(R.id.character_char_text);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(pos));
                }
            });
        }
    }
    // create on listener
    public interface OnItemClickListener {
        void onItemClick(Character character);
    }
    // set the on click listener
    public void setOnItemClickListener(CharacterListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}