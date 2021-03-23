package com.example.dungeons_and_dragons;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.dungeons_and_dragons.data.AppDB;
import com.example.dungeons_and_dragons.data.DAndDDao;
import com.example.dungeons_and_dragons.model.User;


public class MainActivity extends AppCompatActivity {
    private AppDB database;

    private DAndDDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  find the edit text and button by id
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.registerBtn);
        // set the intents
        final Intent registerIntent = new Intent(this, RegisterActivity.class);
        final Intent navActivityIntent = new Intent(this, NavActivity.class);
        // build the database
        database = Room.databaseBuilder(this, AppDB.class, "DAndDDatabase")
                .allowMainThreadQueries()
                .build();
        // set DAO
        userDao = database.dAndDDao();

        // set on click listeners
        registerButton.setOnClickListener(v -> MainActivity.this.startActivity(registerIntent));
        loginButton.setOnClickListener((v) -> {
            // find user by edit username and passowrd provided
            User user = userDao.getUser(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            // if user exists start the app
            if (user != null) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                MainActivity.this.startActivity(navActivityIntent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
