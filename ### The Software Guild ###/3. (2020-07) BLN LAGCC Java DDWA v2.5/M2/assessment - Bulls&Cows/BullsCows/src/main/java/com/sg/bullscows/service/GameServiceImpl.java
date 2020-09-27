package com.sg.bullscows.service;

import com.sg.bullscows.dto.*;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class GameServiceImpl implements GameService {
    
    int gameID = 0;
    
    @Override
    public void beginGame() {
        throw new UnsupportedOperationException(".");
    }

    @GetMapping("/{id}")
    public List<Game> getAllGames() {
        List<Game> games = dao.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameByID(int gameID) {
        throw new UnsupportedOperationException(".");
    }

    private int generateGameID() {
        gameID++;
        return gameID;
    }



    @Override
    public Round guessRound() {
        throw new UnsupportedOperationException(".");
    }

    @GetMapping("/{id}")
    public List<Round> getAllRounds() {
        throw new UnsupportedOperationException(".");
    }

    @GetMapping("/{id}")
    public List<Round> getAllRoundsByGame(Game game) {
        throw new UnsupportedOperationException(".");
    }
}
