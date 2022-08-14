package com.amaromerovic.countryflaggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class endScreen extends AppCompatActivity {
    private Button retakeButton;
    private Button quitButtonEndScreen;
    private TextView pointView;
    private final int NO_QUESTIONS_PER_QUIZ = 10;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        retakeButton = findViewById(R.id.retakeButton);
        quitButtonEndScreen = findViewById(R.id.quitButtonEndScreen);
        pointView = findViewById(R.id.pointView);

        Bundle bundle = getIntent().getExtras();
        pointView.setText(bundle.getInt("Points") + "/" + NO_QUESTIONS_PER_QUIZ);


        retakeButton.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        quitButtonEndScreen.setOnClickListener(view -> finish());
    }
}