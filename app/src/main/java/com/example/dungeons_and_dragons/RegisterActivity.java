package com.example.dungeons_and_dragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.dungeons_and_dragons.model.AppViewModel;
import com.example.dungeons_and_dragons.model.User;

import java.util.List;


public class RegisterActivity extends AppCompatActivity {

    private AppViewModel appViewModel; // get the view model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // set the view model
        appViewModel = new ViewModelProvider(this)
                .get(AppViewModel.class);
        //get the edit text and button by id
        final EditText nameText = findViewById(R.id.regNameText);
        final EditText userNameText = findViewById(R.id.regUserNameText);
        final EditText emailText = findViewById(R.id.regEmailText);
        final EditText passwordText = findViewById(R.id.regPasswordText);
        final EditText confirmPasswordText = findViewById(R.id.regConfirmPassText);
        final Button createAcctBtn = findViewById(R.id.createAcctButton);
        final Intent activityIntent = new Intent(this, MainActivity.class);
        // set the listener
        createAcctBtn.setOnClickListener(v -> {
            if (passwordText.getText().toString().equals(confirmPasswordText.getText().toString())) {
                // put the registered user into an object
                User newUser = new User(nameText.getText().toString(), userNameText.getText().toString(),
                        emailText.getText().toString(), passwordText.getText().toString());
                appViewModel.insert(newUser); // insert new user into db
                startActivity(activityIntent); // go to login page
            } else {
                Toast.makeText(getApplication().getBaseContext(), "Passwords do not match", Toast.LENGTH_SHORT);
            }
        });
    }
}