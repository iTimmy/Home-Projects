package com.example.demo.models;

public class Games {

    private int gameID;
    private String answers;
    private boolean isFinished;
    // private Round round;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    // public Round getRound() {
    //     return round;
    // }

    // public void setRound(Round round) {
    //     this.round = round;
    // }
}