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
import org.springframework.beans.factory.annotation.Autowired;
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

    GameService service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game begin(@RequestBody Game newGame) {
        return service.beginGame(newGame);
    }

    @PutMapping("/guess")
    public Round guess(@RequestBody Round newRound) {
        return service.addRound(newRound);
    }

    @GetMapping("/games")
    public List<Game> game() {
        return service.getAllGames();
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
        Game result = service.getGameByID(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/rounds/{id}")
    public ResponseEntity<List<Round>> rounds(@PathVariable int id) {
        Game game = service.getGameByID(id);
        List<Round> result = service.getAllRoundsByGame(game);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

}
