/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.dao;

import com.sg.bullscows.dto.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author timmy
 */
public class RoundDaoDB implements RoundDao {
    
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

    private void saveRound() {

    }
    
    private void loadRound() {
        
    }
}