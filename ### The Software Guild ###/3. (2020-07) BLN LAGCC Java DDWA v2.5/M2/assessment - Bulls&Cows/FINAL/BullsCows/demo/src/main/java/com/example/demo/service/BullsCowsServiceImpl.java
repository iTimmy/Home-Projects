package com.example.demo.service;

import com.example.demo.memory.*;
import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BullsCowsServiceImpl implements BullsCowsService {

    @Autowired
    GameDao gameDao;
    @Autowired
    RoundDao roundDao;

    @Override
    public int beginGame(Games newGame) {
        newGame.setAnswers(String.valueOf(generateAnswer()));
        return gameDao.beginGame(newGame);
    }

    @Override
    public List<Games> getAllGames() {
        List<Games> games = gameDao.getAllGames();
        for (Games currentGame : games) {
            if (!currentGame.isFinished()) {
                currentGame.setAnswers("----");
            }
        }
        return games;
    }

    @Override
    public Games getGameByID(int id) {
        Games game = gameDao.getGameByID(id);
        if (!game.isFinished()) {
            game.setAnswers("----");
        }
        return game;
    }

    @Override
    public void update(int gameID) {
        gameDao.update(gameID);
    }

    @Override
    public Round beginRound(Round newRound, Games game) {
        Games currentGame = gameDao.getGameByID(newRound.getGameID());
        String answer = currentGame.getAnswers();
        String guess = newRound.getGuess();
        
        String result = checkAnswer(guess, answer);
        newRound.setResult(result);

        if (currentGame.isFinished()) {
            return null;
        } else if (newRound.getResult().equals("e:4:p:0")) {
            gameDao.update(newRound.getGameID());
        }

        return roundDao.beginRound(newRound, game);
    }

    @Override
    public List<Round> getAllRoundsByGameID(int gameID) {
        if (gameDao.getGameByID(gameID) == null) {
            return null;
        }
        return roundDao.getAllRoundsByGameID(gameID);
    }

    private String generateAnswer() {
        int i = 0;
        String generatedAnswer = "";
        int generatedDigit = 0;
        while (i != 4) {
            generatedDigit = (int) (Math.random() * 10);
            if (generatedAnswer.equals("") || !generatedAnswer.contains(String.valueOf(generatedDigit))) {
                generatedAnswer += String.valueOf(generatedDigit);
                i++;
            } 
        }
        return generatedAnswer;
    }

    private String checkAnswer(String guess, String answer) {
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < 4; i++) {
            if (answer.charAt(i) == guess.charAt(i)) {
                exact++;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    if (answer.charAt(i) == guess.charAt(j)) {
                        partial++;
                    }
                }
            }
        }

        String result = "e:" + exact + ":p:" + partial;
        return result;
    }

}
