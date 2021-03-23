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
import com.example.dungeons_and_dragons.model.Character;
import com.example.dungeons_and_dragons.ui.AddEditCharacterActivity;
import com.example.dungeons_and_dragons.ui.CharacterListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ConcurrentHashMap;

public class CharacterFragment extends Fragment {
    public static final int ADD_CHARACTER_REQUEST = 1;
    public static final int EDIT_CHARACTER_REQUEST = 2;
    private static final int RESULT_OK = -1;
    private AppViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign, container, false);
        // set the floating action button
        FloatingActionButton floatingActionButton = view.findViewById(R.id.button_add_campaign);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getBaseContext(), AddEditCharacterActivity.class);
            startActivityForResult(intent, ADD_CHARACTER_REQUEST); // start activity
        });
        // find recyclerview  by id
        RecyclerView recyclerView = view.findViewById(R.id.campaign_recycler_view);
        // set the linear layout and set the adapter to recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        CharacterListAdapter adapter = new CharacterListAdapter();
        recyclerView.setAdapter(adapter);
        //set the viewmodel
        viewModel = new ViewModelProvider(this)
                .get(AppViewModel.class);
        viewModel.getAllCharacters().observe(this.getViewLifecycleOwner(), characters -> adapter.submitList(characters));
        // set the touch helper for deleting objects
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getCharacterAt(viewHolder.getAdapterPosition())); // detelete object from the db
                Toast.makeText(getActivity().getBaseContext(), "Campaign Deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView); // attach recyclerview to the item touch
        // set the item click listener
        adapter.setOnItemClickListener((Character character) -> {
            Intent intent = new Intent(getActivity().getBaseContext(), AddEditCharacterActivity.class);
            intent.putExtra(AddEditCharacterActivity.EXTRA_ID, character.getCharacterId());
            intent.putExtra(AddEditCharacterActivity.EXTRA_NAME, character.getName());
            intent.putExtra(AddEditCharacterActivity.EXTRA_LEVEL, character.getLevel());
            intent.putExtra(AddEditCharacterActivity.EXTRA_STRENGTH, character.getStrength());
            intent.putExtra(AddEditCharacterActivity.EXTRA_CONSTITUTION, character.getConstitution());
            intent.putExtra(AddEditCharacterActivity.EXTRA_DEXTERITY, character.getDexterity());
            intent.putExtra(AddEditCharacterActivity.EXTRA_INTELLIGENCE, character.getIntelligence());
            intent.putExtra(AddEditCharacterActivity.EXTRA_WISDOM, character.getWisdom());
            intent.putExtra(AddEditCharacterActivity.EXTRA_CHARISMA, character.getCharisma());
            startActivityForResult(intent, EDIT_CHARACTER_REQUEST);
        });

        return view;



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get data from the intent
        if (requestCode == ADD_CHARACTER_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditCharacterActivity.EXTRA_NAME);
            int level = data.getIntExtra(AddEditCharacterActivity.EXTRA_LEVEL, 1);
            int strength = data.getIntExtra(AddEditCharacterActivity.EXTRA_STRENGTH, 1);
            int constitution = data.getIntExtra(AddEditCharacterActivity.EXTRA_CONSTITUTION, 1);
            int dexterity = data.getIntExtra(AddEditCharacterActivity.EXTRA_DEXTERITY, 1);
            int intelligence = data.getIntExtra(AddEditCharacterActivity.EXTRA_INTELLIGENCE, 1);
            int wisdom = data.getIntExtra(AddEditCharacterActivity.EXTRA_WISDOM, 1);
            int charisma = data.getIntExtra(AddEditCharacterActivity.EXTRA_CHARISMA, 1);

            Character character = new Character(name, level, strength, constitution, dexterity, intelligence, wisdom, charisma);
            viewModel.insert(character);

            Toast.makeText(getActivity().getBaseContext(), "Character Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_CHARACTER_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditCharacterActivity.EXTRA_ID, -1);
            // id id is negative db cannot be updated
            if (id == -1) {
                Toast.makeText(getActivity().getBaseContext(), "Character could not be Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            // get data from intent
            String name = data.getStringExtra(AddEditCharacterActivity.EXTRA_NAME);
            int level = data.getIntExtra(AddEditCharacterActivity.EXTRA_LEVEL, 1);
            int strength = data.getIntExtra(AddEditCharacterActivity.EXTRA_STRENGTH, 1);
            int constitution = data.getIntExtra(AddEditCharacterActivity.EXTRA_CONSTITUTION, 1);
            int dexterity = data.getIntExtra(AddEditCharacterActivity.EXTRA_DEXTERITY, 1);
            int intelligence = data.getIntExtra(AddEditCharacterActivity.EXTRA_INTELLIGENCE, 1);
            int wisdom = data.getIntExtra(AddEditCharacterActivity.EXTRA_WISDOM, 1);
            int charisma = data.getIntExtra(AddEditCharacterActivity.EXTRA_CHARISMA, 1);
            // set data from intent and update
            Character character = new Character(name, level, strength, constitution, dexterity, intelligence, wisdom, charisma);
            character.setCharacterId(id);
            viewModel.update(character);

            Toast.makeText(getActivity().getBaseContext(), "Character Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getBaseContext(), "Could not save Character", Toast.LENGTH_SHORT).show();
        }
    }
}
