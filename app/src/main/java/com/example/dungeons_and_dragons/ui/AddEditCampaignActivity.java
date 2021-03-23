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
import com.example.dungeons_and_dragons.model.Campaign;


public class AddEditCampaignActivity extends AppCompatActivity {
    // get the final string dor the intent
    public static final String EXTRA_ID =
            "com.example.dungeons_and_dragons.ui.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.dungeons_and_dragons.ui.EXTRA_TITLE";

    private EditText campaignName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_campaign);

        campaignName = findViewById(R.id.campaign_name_edittext); // set by ID


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        // get data from intent
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Monster"); // set title to edit
            campaignName.setText(intent.getStringExtra(EXTRA_TITLE));
        } else {
            setTitle("Add Monster"); // set title to add
        }
    }
    /** Save the campaign */
    private void saveCampaign() {
        String name = campaignName.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this,"Please enter a Name", Toast.LENGTH_SHORT);
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, name);

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
        menuInflater.inflate(R.menu.add_campaign_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_campaign:
                saveCampaign();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
