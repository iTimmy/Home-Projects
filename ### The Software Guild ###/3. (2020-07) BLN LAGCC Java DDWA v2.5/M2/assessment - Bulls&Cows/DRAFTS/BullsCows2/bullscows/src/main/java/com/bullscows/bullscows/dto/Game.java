package com.bullscows.bullscows.dto;

public class Game {
    private int gameID;
    private int userID;
    private String answers; 
    private boolean isFinished;
    private Round round;

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int UserID) {
        this.userID = userID;
    }


    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    
}