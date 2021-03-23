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
import com.example.dungeons_and_dragons.model.Monster;
import com.example.dungeons_and_dragons.ui.AddEditMonsterActivity;
import com.example.dungeons_and_dragons.ui.MonsterListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MonstersFragment extends Fragment {
    public static final int ADD_MONSTER_REQUEST = 1;
    public static final int EDIT_MONSTER_REQUEST = 2;
    private static final int RESULT_OK = -1;

    private AppViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign, container, false);
        // grab the floating action button
        FloatingActionButton floatingActionButton = view.findViewById(R.id.button_add_campaign);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getBaseContext(), AddEditMonsterActivity.class);
            startActivityForResult(intent, ADD_MONSTER_REQUEST); // start the add monster activity
        });

        RecyclerView recyclerView = view.findViewById(R.id.campaign_recycler_view); // get recyclerview by id
        // get linear layout and set recylerview by adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        MonsterListAdapter adapter = new MonsterListAdapter();
        recyclerView.setAdapter(adapter);
        // set viewmodel and observer
        viewModel = new ViewModelProvider(this)
                .get(AppViewModel.class);
        viewModel.getAllMonsters().observe(this.getViewLifecycleOwner(), monster -> adapter.submitList(monster));
        // add a touch object for the delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getMonsterAt(viewHolder.getAdapterPosition())); // delete the object from database
                Toast.makeText(getActivity().getBaseContext(), "Monster Deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
        //set the on click listener
        adapter.setOnItemClickListener((Monster monster) -> {
            Intent intent = new Intent(getActivity().getBaseContext(), AddEditMonsterActivity.class);
            // add the id, name, and level to the intent
            intent.putExtra(AddEditMonsterActivity.EXTRA_ID, monster.getId());
            intent.putExtra(AddEditMonsterActivity.EXTRA_TITLE, monster.getName());
            intent.putExtra(AddEditMonsterActivity.EXTRA_LEVEL, monster.getLevel());
            startActivityForResult(intent, EDIT_MONSTER_REQUEST); // start activity
        });

        return view;



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get data from the intent
        if (requestCode == ADD_MONSTER_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditMonsterActivity.EXTRA_TITLE);
            String level = data.getStringExtra(AddEditMonsterActivity.EXTRA_LEVEL);
            // set data from intent then insert
            Monster monster = new Monster(name, level);
            viewModel.insert(monster);

            Toast.makeText(getActivity().getBaseContext(), "Monster Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_MONSTER_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditMonsterActivity.EXTRA_ID, -1);
            // id id is negative db cannot be updated
            if (id == -1) {
                Toast.makeText(getActivity().getBaseContext(), "Campaign could not be Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            // get data from intent
            String name = data.getStringExtra(AddEditMonsterActivity.EXTRA_TITLE);
            String level = data.getStringExtra(AddEditMonsterActivity.EXTRA_LEVEL);
            // set data from intent and update
            Monster monster = new Monster(name, level);
            monster.setId(id);
            viewModel.update(monster);

            Toast.makeText(getActivity().getBaseContext(), "Monster Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getBaseContext(), "Could not save Monster", Toast.LENGTH_SHORT).show();
        }
    }
}

