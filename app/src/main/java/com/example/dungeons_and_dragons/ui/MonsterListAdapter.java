package com.example.dungeons_and_dragons.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dungeons_and_dragons.R;
import com.example.dungeons_and_dragons.model.Monster;


public class MonsterListAdapter extends ListAdapter<Monster, MonsterListAdapter.MonsterViewHolder> {
    private OnItemClickListener listener;

    public MonsterListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Monster> DIFF_CALLBACK = new DiffUtil.ItemCallback<Monster>() {
        // check if the items have the same id
        @Override
        public boolean areItemsTheSame(@NonNull Monster oldItem, @NonNull Monster newItem) {
            return oldItem.getId() == newItem.getId();
        }
        // check to see if the names are the same
        @Override
        public boolean areContentsTheSame(@NonNull Monster oldItem, @NonNull Monster newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getLevel() == newItem.getLevel();
        }
    };
    @NonNull
    @Override
    public MonsterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.monster_initiative_item, viewGroup, false);

        return new MonsterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonsterViewHolder charViewHolder, int position) {
        // set the text from db
        Monster current = getItem(position);
        charViewHolder.monsterTextView.setText(current.getName());
        charViewHolder.monsterLevelTextView.setText(String.valueOf(current.getLevel()));

    }
    // get campaign at position
    public Monster getMonsterAt(int pos) {
        return getItem(pos);
    }


    public class MonsterViewHolder extends RecyclerView.ViewHolder {
        private TextView monsterTextView;
        private TextView monsterLevelTextView;

        public MonsterViewHolder(@NonNull View itemView) {
            super(itemView);
            monsterTextView = itemView.findViewById(R.id.monster_name);
            monsterLevelTextView = itemView.findViewById(R.id.monster_level_text);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(pos));
                }
            });
        }
    }
    // Set the listener
    public interface OnItemClickListener {
        void onItemClick(Monster monster);
    }
    // set the on click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
