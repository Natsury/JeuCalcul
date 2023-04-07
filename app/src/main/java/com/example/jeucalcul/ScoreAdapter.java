package com.example.jeucalcul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jeucalcul.model.entities.Score;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Score> {

    private Context context;
    private List<Score> scores;

    public ScoreAdapter(Context context, List<Score> scores) {
        super(context, R.layout.list_item_score, scores);
        this.context = context;
        this.scores = scores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_score, null);
        }

        TextView nicknameTextView = view.findViewById(R.id.nicknameTextView);
        TextView scoreTextView = view.findViewById(R.id.scoreTextView);

        Score score = scores.get(position);

        nicknameTextView.setText(score.getNickname());
        scoreTextView.setText(String.valueOf(score.getScore()));

        return view;
    }
}
