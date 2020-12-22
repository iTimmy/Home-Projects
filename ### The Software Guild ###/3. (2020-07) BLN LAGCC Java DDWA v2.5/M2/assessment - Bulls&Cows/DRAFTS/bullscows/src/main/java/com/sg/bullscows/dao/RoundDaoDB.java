package com.sg.bullscows.dao;

import java.util.*;
import com.sg.bullscows.dto.*;
import org.springframework.web.bind.annotation.GetMapping;

public class RoundDaoDB implements RoundDao {

    @Override
    public Round guessRound() {
        throw new UnsupportedOperationException("ok");
    }

    @Override
    @GetMapping
    public List<Round> getAllRounds() {
        throw new UnsupportedOperationException("ok");
    }

    @Override
    @GetMapping("/{id}")
    public List<Round> getAllRoundsByGame(Game game) {
        throw new UnsupportedOperationException("ok");
    }

}