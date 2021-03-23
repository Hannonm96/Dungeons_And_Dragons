package com.example.dungeons_and_dragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dungeons_and_dragons.model.AppViewModel;
import com.example.dungeons_and_dragons.model.Campaign;
import com.example.dungeons_and_dragons.ui.AddEditCampaignActivity;
import com.example.dungeons_and_dragons.ui.AppListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class CampaignFragment extends Fragment {
    public static final int ADD_CAMPAIGN_REQUEST = 1;
    public static final int EDIT_CAMPAIGN_REQUEST = 2;
    private static final int RESULT_OK = -1;

    private AppViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign, container, false);
        // grab the floating action button
        FloatingActionButton floatingActionButton = view.findViewById(R.id.button_add_campaign);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getBaseContext(), AddEditCampaignActivity.class);
            startActivityForResult(intent, ADD_CAMPAIGN_REQUEST); // start the add monster activity
        });

        RecyclerView recyclerView = view.findViewById(R.id.campaign_recycler_view); // get recyclerview by id
        // get linear layout and set recylerview by adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        AppListAdapter adapter = new AppListAdapter();
        recyclerView.setAdapter(adapter);
        // set viewmodel and observer
        viewModel = new ViewModelProvider(this)
                .get(AppViewModel.class);
        viewModel.getAllCampaigns().observe(this.getViewLifecycleOwner(), campaigns -> adapter.submitList(campaigns));
        // add a touch object for the delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getCampaignAt(viewHolder.getAdapterPosition())); // delete the object from database
                Toast.makeText(getActivity().getBaseContext(), "Campaign Deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
        //set the on click listener
        adapter.setOnItemClickListener((Campaign campaign) -> {
            Intent intent = new Intent(getActivity().getBaseContext(), AddEditCampaignActivity.class);
            intent.putExtra(AddEditCampaignActivity.EXTRA_ID, campaign.getCampaignId());
            intent.putExtra(AddEditCampaignActivity.EXTRA_TITLE, campaign.getName());
            startActivityForResult(intent, EDIT_CAMPAIGN_REQUEST);
        });

        return view;



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get data from the intent
        if (requestCode == ADD_CAMPAIGN_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditCampaignActivity.EXTRA_TITLE);
            // set data from intent then insert
            Campaign campaign = new Campaign(name);
            viewModel.insert(campaign);

            Toast.makeText(getActivity().getBaseContext(), "Campaign Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_CAMPAIGN_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditCampaignActivity.EXTRA_ID, -1);
            // id id is negative db cannot be updated
            if (id == -1) {
                Toast.makeText(getActivity().getBaseContext(), "Campaign could not be Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditCampaignActivity.EXTRA_TITLE);
            // get data from intent
            Campaign campaign = new Campaign(name);
            campaign.setCampaignId(id);
            viewModel.update(campaign);
            // set data from intent and update
            Toast.makeText(getActivity().getBaseContext(), "Campaign Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getBaseContext(), "Could not save Campaign", Toast.LENGTH_SHORT).show();
        }
    }
}
