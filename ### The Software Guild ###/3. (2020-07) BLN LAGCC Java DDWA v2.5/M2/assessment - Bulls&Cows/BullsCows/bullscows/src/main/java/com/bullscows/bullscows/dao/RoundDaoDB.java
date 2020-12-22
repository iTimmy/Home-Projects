/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bullscows.bullscows.dao;

import com.bullscows.bullscows.dto.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author timmy
 */

@Repository
@Profile("memory")
public class RoundDaoDB implements RoundDao {

    private static final List<Round> rounds = new ArrayList<>();
    
    @Override
    public Round addRound(Round newRound) {
        throw new UnsupportedOperationException(".");
    }
    
    @Override
    public List<Round> getAllRounds() {
        throw new UnsupportedOperationException(".");
    }
    
    @Override
    public List<Round> getAllRoundsByGame(Game game) {
        Round round = game.getRound();
        // for (Round currentRound : Game) {

        // }
        return rounds;
    }

}