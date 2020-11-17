package com.sg.bullscows.dto;

import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PTGIN
 */
public class round {
    
private String guess; 
private int roundId;
 private String result;
 private int gameId;
 private LocalDateTime roundtime;

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getRoundtime() {
        return roundtime;
    }

    public void setRoundtime(LocalDateTime roundtime) {
        this.roundtime = roundtime;
    }
 

    
}

//roundId INT PRIMARY KEY  AUTO_INCREMENT,
//roundtime datetime,
//guess varchar(4),
//gameId INT NOT NULL,
//result VARCHAR(7),
