/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bullscows.bullscows.controller;

import java.util.List;

import com.bullscows.bullscows.dto.Game;
import com.bullscows.bullscows.dto.Round;
import com.bullscows.bullscows.service.*;
import com.bullscows.bullscows.view.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {
    View view = new View();
    private final GameService service;
    int round = 0;

    public GameController(GameService service) {
        this.service = service;
    }

    public void userAnswer() {
        int userAnswer = view.userAnswer();
        view.checkAnswer(userAnswer, displayAnswer());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game begin(@RequestBody Game game) {
        return service.beginGame(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity guess(@PathVariable int id, @RequestBody Game game) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != game.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.update(game)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @GetMapping
    public List<Game> game() {
        return service.getAllGames();
    }

    @GetMapping("/{gameID}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
        Game result = service.getGameByID(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{gameID}")
    public ResponseEntity<List<Round>> rounds(@PathVariable int id) {
        Game game = service.getGameByID(id);
        List<Round> result = service.getAllRoundsByGame(game);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
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
