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
import com.example.dungeons_and_dragons.model.Campaign;

public class AppListAdapter extends ListAdapter<Campaign, AppListAdapter.AppViewHolder> {
        private OnItemClickListener listener;

        public AppListAdapter() {
            super(DIFF_CALLBACK);
        }

        private static final DiffUtil.ItemCallback<Campaign> DIFF_CALLBACK = new DiffUtil.ItemCallback<Campaign>() {
            // check if the items have the same id
            @Override
            public boolean areItemsTheSame(@NonNull Campaign oldItem, @NonNull Campaign newItem) {
                return oldItem.getCampaignId() == newItem.getCampaignId();
            }
            // check to see if the names are the same
            @Override
            public boolean areContentsTheSame(@NonNull Campaign oldItem, @NonNull Campaign newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        };



        @NonNull
        @Override
        public AppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.campaign_item, viewGroup, false);

            return new AppViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AppViewHolder appViewHolder, int position) {
            // set the text from db
            Campaign current = getItem(position);
            appViewHolder.campaignTextView.setText(current.getName());

        }
        // get campaign at position
        public Campaign getCampaignAt(int pos) {
            return getItem(pos);
        }

        public class AppViewHolder extends RecyclerView.ViewHolder {
            private TextView campaignTextView;

            public AppViewHolder(@NonNull View itemView) {
                super(itemView);
                campaignTextView = itemView.findViewById(R.id.campaign_name);

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
            void onItemClick(Campaign campaign);
        }
        // set on click listener
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
}
