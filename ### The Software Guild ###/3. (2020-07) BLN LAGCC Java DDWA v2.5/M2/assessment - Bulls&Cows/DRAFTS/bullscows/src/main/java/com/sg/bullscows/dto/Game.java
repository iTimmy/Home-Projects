package com.sg.bullscows.dto;

public class Game {
    private int gameID;
    private int userID;
    private String answers;
    private boolean isFinished;

    public int gameID() {
        return gameID;
    }

    public void gameID(int gameID) {
        this.gameID = gameID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
}
