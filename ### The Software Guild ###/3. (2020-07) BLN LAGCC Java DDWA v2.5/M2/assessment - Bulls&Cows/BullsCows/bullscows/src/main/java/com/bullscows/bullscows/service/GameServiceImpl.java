package com.bullscows.bullscows.service;

import com.bullscows.bullscows.dao.*;
import com.bullscows.bullscows.dto.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class GameServiceImpl implements GameService {
    
    @Autowired
    GameDao gameDao;
    RoundDao roundDao;
    
    @Override
    public Game beginGame(Game newGame) {
        return gameDao.beginGame(newGame);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        return games;
    }

    @Override
    public Game getGameByID(int gameID) {
        return gameDao.getGameByID(gameID);
    }

    @Override
    public Round addRound(Round newRound) {
        return roundDao.addRound(newRound);
    }

    @Override
    public List<Round> getAllRounds() {
        List<Round> rounds = roundDao.getAllRounds();
        return rounds;
    }

    @Override
    public List<Round> getAllRoundsByGame(Game game) {
        if (gameDao.getGameByID(game.getGameID()) == null) {

        }
        List<Round> rounds = roundDao.getAllRoundsByGame(game);
        return rounds;
    }
}
