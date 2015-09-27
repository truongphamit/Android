package com.example.truongpq.scoreboardbadminton;

/**
 * Created by truongpq on 9/6/15.
 */
public class PlayerSet {
    private String name;
    private int score;
    private int winSet;

    public int getWinSet() {
        return winSet;
    }

    public void setWinSet(int winSet) {
        this.winSet = winSet;
    }

    public PlayerSet(String name) {
        this.name = name;
    }

    public void addScore() {
        ++score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWinSet() {
        ++winSet;
    }

}
