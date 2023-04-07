package com.example.jeucalcul;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    private Button buttonMainMenuAbout;
    private Button buttonCredits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        buttonMainMenuAbout = findViewById(R.id.buttonMainMenuAbout);
        buttonMainMenuAbout.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
        buttonCredits = findViewById(R.id.buttonCredits);
        buttonCredits.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreditsActivity.class);
            startActivity(intent);
        });
    }
}
