/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bullscows.bullscows.dao;

import com.bullscows.bullscows.dto.*;

import java.time.*;
import java.util.*;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author timmy
 */
@Repository
@Profile("memory")
public class GameDaoDB implements GameDao {

    private static final List<Game> games = new ArrayList<>();
    
    @Override
    public Game beginGame(Game newGame) {
        int gameID = games.stream()
                .mapToInt(i -> i.getGameID())
                .max()
                .orElse(0) + 1;

        Round newRound = new Round();
        newRound.setRoundID(1);
        newRound.setGuess("hey");
        newRound.setResult("hi");
        // newRound.setRoundtime(date);

        newGame.setGameID(gameID);
        newGame.setAnswers("hi");
        newGame.setIsFinished(true);
        newGame.setRound(newRound);

        games.add(newGame);

        return newGame;
    }
    
    @Override
    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }
    
    @Override
    public Game getGameByID(int gameID) {
        for (Game currentGame : games) {
            if (gameID == currentGame.getGameID()) {
                return currentGame;
            }
        }
        return null;
    }

    // @Override
    // public void updateGame() {
        
    // }

}
