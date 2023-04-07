package com.example.jeucalcul.model.entities;

public class Score extends BaseEntity {
    private String nickname;
    private Integer score;

    public String getNickname() {
        return nickname;
    }

    public Integer getScore() {
        return score;
    }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Score(String nickname, Integer score) {
        this.nickname = nickname;
        this.score = score;
    }
}
