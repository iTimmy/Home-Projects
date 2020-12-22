package com.example.demo.models;

import java.time.*;

public class Round {
    private int gameID;
    private int roundID;
    private String guess;
    private String result;
    private String roundtime;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRoundtime() {
        return roundtime;
    }

    public void setRoundtime(String roundtime) {
        this.roundtime = roundtime;
    }
}
