package com.example.truongpq.scoreboardbadminton;

/**
 * Created by truongpq on 9/6/15.
 */
public class Set {
    private PlayerSet player1;
    private PlayerSet player2;

    public Set(PlayerSet player1, PlayerSet player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayerSet getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerSet player1) {
        this.player1 = player1;
    }

    public PlayerSet getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerSet player2) {
        this.player2 = player2;
    }

    public int checkWinSet() {
        if (player1.getScore() >= 25 && (player1.getScore() - player2.getScore()) >= 2) {
            return 1;
        } else if (player2.getScore() >= 25 && (player2.getScore() - player1.getScore()) >= 2) {
            return 2;
        } else {
            return 0;
        }
    }

    public int checkWin() {
        if (player1.getWinSet() == 2) {
            return 1;
        } else if (player2.getWinSet() == 2) {
            return 2;
        } else {
            return 0;
        }
    }

}
