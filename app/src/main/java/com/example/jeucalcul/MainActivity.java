package com.example.jeucalcul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button highscoresButton;
    private Button aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        highscoresButton = findViewById(R.id.highscoresButton);
        aboutButton = findViewById(R.id.aboutButton);

        playButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent);
        });

        highscoresButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,HighscoresActivity.class);
            startActivity(intent);
        });

        aboutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        });
    }
}