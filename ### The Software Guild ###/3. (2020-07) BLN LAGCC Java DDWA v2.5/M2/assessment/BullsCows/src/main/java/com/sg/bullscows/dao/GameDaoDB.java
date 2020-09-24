/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.dao;

import com.sg.bullscows.dto.*;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author timmy
 */
public class GameDaoDB implements GameDao {
    
    @Override
    public void beginGame() {
        generateAnswer();
    }
    
    @GetMapping("/{id}")
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException(".");
    }
    
    @GetMapping("/{id}")
    public Game getGameByID(Game game) {
        throw new UnsupportedOperationException(".");
    }

    @PostMapping
    private int generateAnswer() {
        double generatedAnswer = Math.random() * 100;
        int answer = (int)generatedAnswer;
        return answer;
    }
}
