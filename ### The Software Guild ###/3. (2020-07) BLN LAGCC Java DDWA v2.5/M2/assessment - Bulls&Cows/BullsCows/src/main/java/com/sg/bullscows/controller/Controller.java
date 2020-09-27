/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.controller;

import com.sg.bullscows.dto.Game;
import com.sg.bullscows.dto.Round;
import com.sg.bullscows.service.*;
import com.sg.bullscows.view.*;

/**
 *
 * @author timmy
 */
public class Controller {
    View view = new View();
    GameService service = new GameServiceImpl();

    int round = 0;
    


    public void userAnswer() {
        int userAnswer = view.userAnswer();
        view.checkAnswer(userAnswer, displayAnswer());

    }

    public void beginGame() {
        service.beginGame();
    }

    public void displayRound(int round) {
        view.displayRound(round);
    }

    public void terminate() {
        view.displayTermination();
    }

    public int displayAnswer() {
        int showAnswer = view.generateAnswer();
        view.displayShowAnswer(showAnswer);
        return showAnswer;
    }




    public void run() {
        int generatedAnswer = view.generateAnswer();
        int userAnswer = 0;

        while (userAnswer != generatedAnswer) {
            view.display();
            round++;
            displayRound(round);
            
            userAnswer = view.userAnswer();
            view.checkAnswer(userAnswer, generatedAnswer);

            // saves round into dao
            Round currentRound = view.saveRound(round, generatedAnswer, userAnswer);
            Game currentGame = service.getGameByID(12);
            currentGame.setRound(currentRound);
            currentGame.setAnswers(Integer.toString(userAnswer));

        }
        view.displaySuccess();
        System.out.println("Total rounds: " + round);
        totalRounds(round);
    }

    public void totalRounds(int round) {
        System.out.println("Total rounds: " + round);
    }

}
