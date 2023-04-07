package com.example.jeucalcul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jeucalcul.DAO.ScoreBaseHelper;
import com.example.jeucalcul.DAO.ScoreDao;
import com.example.jeucalcul.model.entities.Score;

public class SaveScoreActivity extends AppCompatActivity {
    private int score;
    private TextView textViewScore;
    private EditText editTextNickaname;
    private Button buttonSaveScore;
    private Button buttonMainMenu;
    private ScoreDao scoreDao;
    private String difficulte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savescore);
        textViewScore = findViewById(R.id.textViewScore);
        editTextNickaname = findViewById(R.id.editTextNickname);
        buttonSaveScore = findViewById(R.id.buttonSaveScore);
        buttonMainMenu = findViewById(R.id.buttonMainMenu);

        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE", 0);
        difficulte = intent.getStringExtra("difficulte");

        textViewScore.setText(textViewScore.getText().toString() + " " + score);
        buttonSaveScore.setOnClickListener(view -> SaveScore());
        buttonMainMenu.setOnClickListener(view -> MainMenu());
        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "BDD", 1));
    }

    private void MainMenu() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void SaveScore() {
        String nickname = editTextNickaname.getText().toString();
        if (nickname.isEmpty()) {
            Toast.makeText(this, getString(R.string.ERROR_EMPTY_NYCKNAME), Toast.LENGTH_SHORT).show();

        } else if (nickname.length() > 20) {
            Toast.makeText(this, getString(R.string.ERROR_NYCKNAME_TOO_LONG), Toast.LENGTH_SHORT).show();

        } else {
            Score ScoreSave = new Score(nickname, score, difficulte);
            scoreDao.create(ScoreSave);
            Toast.makeText(this, getString(R.string.ScoreEnregiste), Toast.LENGTH_SHORT).show();
            MainMenu();
        }
    }
}
