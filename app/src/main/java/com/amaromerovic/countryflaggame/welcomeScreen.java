package com.amaromerovic.countryflaggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class welcomeScreen extends AppCompatActivity {
    private Button playButton;
    private Button quitButtonWelcomeScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        playButton = findViewById(R.id.playButton);
        quitButtonWelcomeScreen = findViewById(R.id.quitButtonWelcomeScreen);

        quitButtonWelcomeScreen.setOnClickListener(view -> finish());

        playButton.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}