package com.example.jeucalcul.model.entities;

import android.util.Log;

public class Score extends BaseEntity {
    private String nickname;
    private Integer score;
    private String difficulte;

    public String getNickname() {
        return nickname;
    }

    public Integer getScore() {
        return score;
    }

    public String getDifficulte() {return difficulte;}

    public void setNickname(String nickname) { this.nickname = nickname; }

    public void setScore(Integer score) {
        this.score = score;
    }
    public void setDifficulte(String difficulte){this.difficulte = difficulte;}

    public Score(String nickname, Integer score, String difficulte) {
        this.nickname = nickname;
        this.score = score;
        this.difficulte = difficulte;
    }
}
