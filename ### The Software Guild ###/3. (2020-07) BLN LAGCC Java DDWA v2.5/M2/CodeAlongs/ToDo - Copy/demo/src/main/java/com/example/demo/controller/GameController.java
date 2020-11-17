package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import com.example.demo.models.*;
import com.example.demo.service.BullsCowsService;

@RestController
@RequestMapping("/bullscows")
public class GameController {

    private final BullsCowsService service;

    public GameController(BullsCowsService service) {
        this.service = service;
    }

    @GetMapping("/games")
    public List<Games> getAllGames() {
        return service.getAllGames();
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int beginGame(@RequestBody Games newGame) {
        return service.beginGame(newGame);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Games> getGameByID(@PathVariable int id) {
        Games result = service.getGameByID(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/rounds/{gameID}")
    public List<Round> getAllRoundsByGameID(@PathVariable int gameID) {
        List<Round> result = service.getAllRoundsByGameID(gameID);
        if (result == null) {
            return (List<Round>) new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @PostMapping("/guess")
    public Round beginRound(@RequestBody Round newRound) {
        Games currentGame = new Games();
        return service.beginRound(newRound, currentGame);
    }

}