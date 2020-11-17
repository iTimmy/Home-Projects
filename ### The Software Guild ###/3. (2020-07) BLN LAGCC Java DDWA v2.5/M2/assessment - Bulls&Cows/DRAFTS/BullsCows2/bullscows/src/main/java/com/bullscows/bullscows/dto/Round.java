package com.bullscows.bullscows.dto;

import java.time.*;

public class Round {
    private int roundID;
    private int gameID;
    private String guess;
    private String result;
    private LocalDateTime roundtime;

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public LocalDateTime getRoundtime() {
        return roundtime;
    }

    public void setRoundtime(LocalDateTime roundtime) {
        this.roundtime = roundtime;
    }
}
