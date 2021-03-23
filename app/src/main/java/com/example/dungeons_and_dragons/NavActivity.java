package com.example.dungeons_and_dragons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener); // set the listener
        setTitle("Campaigns");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                new CampaignFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                // determine which tab will be shown
                switch (item.getItemId()) {
                    case R.id.nav_campaign:
                        setTitle("Campaigns");
                        selectedFragment = new CampaignFragment();
                        break;
                    case R.id.nav_characters:
                        setTitle("Characters");
                        selectedFragment = new CharacterFragment();
                        break;
                    case R.id.nav_monster:
                        setTitle("Monsters");
                        selectedFragment = new MonstersFragment();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                            selectedFragment).commit();
                }
                return true;
            };

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment optionsFragment = null;
        // determine which option will be shown
        switch (item.getItemId()) {
            case  R.id.preferences:
                setTitle("Preferences");
                optionsFragment = new PreferencesFragment();
                break;
            case R.id.help:
                setTitle("Help");
                optionsFragment = new HelpFragment();
                break;
            //default:
            //    return super.onOptionsItemSelected(item);
        }
        if (optionsFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                    optionsFragment).commit();
        }
        return true;
    }
}

