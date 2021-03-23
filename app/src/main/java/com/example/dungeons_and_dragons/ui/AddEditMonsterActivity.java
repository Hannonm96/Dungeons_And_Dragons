package com.example.dungeons_and_dragons.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeons_and_dragons.R;
import com.example.dungeons_and_dragons.model.AppViewModel;
import com.example.dungeons_and_dragons.model.Monster;


public class AddEditMonsterActivity extends AppCompatActivity {
    // set the finsl strings for data
    public static final String EXTRA_ID =
            "com.example.dungeons_and_dragons.ui.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.dungeons_and_dragons.ui.EXTRA_TITLE";
    public static final String EXTRA_LEVEL =
            "com.example.dungeons_and_dragons.ui.EXTRA_LEVEL";
    private EditText monsterName;
    private EditText monsterLevel;
    private AppViewModel appViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monster);
        // get edit text by id
        monsterName = findViewById(R.id.add_monster_name_edittext);
        monsterLevel = findViewById(R.id.add_monster_level_edittext);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent(); // get intent data

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Monster"); // set title to edit monster
            // set edit text from data
            monsterName.setText(intent.getStringExtra(EXTRA_TITLE));
            monsterLevel.setText(intent.getStringExtra(EXTRA_LEVEL));
        } else {
            setTitle("Add Monster"); // set title to add monster
        }
    }
    /** this method packages up data to sent to intent to add or update to db */
    private void saveMonster() {
        String name = monsterName.getText().toString();
        String level = monsterLevel.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a Name", Toast.LENGTH_SHORT);
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, name);
        data.putExtra(EXTRA_LEVEL, level);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_char_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_char:
                saveMonster();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
