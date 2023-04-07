package com.example.jeucalcul;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jeucalcul.DAO.ScoreBaseHelper;
import com.example.jeucalcul.DAO.ScoreDao;
import com.example.jeucalcul.model.entities.Score;

import java.util.List;

public class HighscoresActivity extends AppCompatActivity {
    private ListView listViewHighscores;
    private ScoreDao scoreDao;
    private List<Score> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        listViewHighscores = findViewById(R.id.listViewHighscores);
        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "BDD", 1));
        scores = scoreDao.getHighscores();

        ScoreAdapter adapter = new ScoreAdapter(this, scores);
        listViewHighscores.setAdapter(adapter);
    }
}
