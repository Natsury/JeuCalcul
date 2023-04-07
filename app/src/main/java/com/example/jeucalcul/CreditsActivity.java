package com.example.jeucalcul;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    private TextView creditsTextView;
    private TextView textview_title;

    private ImageView credits_image;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        credits_image = findViewById(R.id.credits_image);
        credits_image.setAnimation(AnimationUtils.loadAnimation(this, R.anim.credits_scroll));
        textview_title = findViewById(R.id.textview_title);
        textview_title.setAnimation(AnimationUtils.loadAnimation(this, R.anim.credits_scroll));
        creditsTextView = findViewById(R.id.credits_text_view);
        creditsTextView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.credits_scroll));

        ScrollView scrollView = findViewById(R.id.scroll_view);
        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mediaPlayer = MediaPlayer.create(this, R.raw.starwarsmainthemefull);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        super.onBackPressed();
    }
}

