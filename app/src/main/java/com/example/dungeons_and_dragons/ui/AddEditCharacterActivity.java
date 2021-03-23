package com.example.dungeons_and_dragons.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dungeons_and_dragons.R;
import com.example.dungeons_and_dragons.model.AppViewModel;
import com.example.dungeons_and_dragons.model.Character;


public class AddEditCharacterActivity extends AppCompatActivity {
    // set the data for activity
    public static final String EXTRA_ID =
            "com.example.dungeons_and_dragons.ui.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.dungeons_and_dragons.ui.EXTRA_NAME";
    public static final String EXTRA_LEVEL =
            "com.example.dungeons_and_dragons.ui.EXTRA_LEVEL";
    public static final String EXTRA_STRENGTH =
            "com.example.dungeons_and_dragons.ui.EXTRA_STRENGTH";
    public static final String EXTRA_CONSTITUTION =
            "com.example.dungeons_and_dragons.ui.EXTRA_CONSTITUTION";
    public static final String EXTRA_DEXTERITY =
            "com.example.dungeons_and_dragons.ui.EXTRA_DEXTERITY";
    public static final String EXTRA_INTELLIGENCE =
            "com.example.dungeons_and_dragons.ui.EXTRA_INTELLIGENCE";
    public static final String EXTRA_WISDOM =
            "com.example.dungeons_and_dragons.ui.EXTRA_WISDOM";
    public static final String EXTRA_CHARISMA =
            "com.example.dungeons_and_dragons.ui.EXTRA_CHARISMA";


    private EditText nameEditText;
    private EditText levelEditText;
    private EditText strEditText;
    private EditText constEditText;
    private EditText dexEditText;
    private EditText intEditText;
    private EditText wisEditText;
    private EditText charEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);

        nameEditText = findViewById(R.id.character_name_edittext);
        levelEditText = findViewById(R.id.character_level_edittext);
        strEditText = findViewById(R.id.character_str_edittext);
        constEditText = findViewById(R.id.character_const_edittext);
        dexEditText = findViewById(R.id.character_dex_edittext);
        intEditText = findViewById(R.id.character_int_edittext);
        wisEditText = findViewById(R.id.character_wis_edittext);
        charEditText = findViewById(R.id.character_char_edittext);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        // get data from intent
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Character");
            nameEditText.setText(intent.getStringExtra(EXTRA_NAME));
            levelEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_LEVEL, 1)));
            strEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_STRENGTH, 1)));
            constEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_CONSTITUTION, 1)));
            dexEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_DEXTERITY, 1)));
            intEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_INTELLIGENCE, 1)));
            wisEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_WISDOM, 1)));
            charEditText.setText(String.valueOf(intent.getIntExtra(EXTRA_CHARISMA, 1)));
        } else {
            setTitle("Add Character");
        }


    }
    /** this method packages up the data from the intent and sends it to another activity  */
    private void saveChar() {
        String name = nameEditText.getText().toString();
        int level = Integer.parseInt(levelEditText.getText().toString());
        int strength = Integer.parseInt(strEditText.getText().toString());
        int constitution = Integer.parseInt(constEditText.getText().toString());
        int dexterity = Integer.parseInt(dexEditText.getText().toString());
        int intelligence = Integer.parseInt(intEditText.getText().toString());
        int wisdom = Integer.parseInt(wisEditText.getText().toString());
        int charisma = Integer.parseInt(charEditText.getText().toString());

        if (name.trim().isEmpty()) {
            Toast.makeText(this,"Please enter a Name", Toast.LENGTH_SHORT);
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_LEVEL, level);
        data.putExtra(EXTRA_STRENGTH, strength);
        data.putExtra(EXTRA_CONSTITUTION, constitution);
        data.putExtra(EXTRA_DEXTERITY, dexterity);
        data.putExtra(EXTRA_INTELLIGENCE, intelligence);
        data.putExtra(EXTRA_WISDOM, wisdom);
        data.putExtra(EXTRA_CHARISMA, charisma);

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
                saveChar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

