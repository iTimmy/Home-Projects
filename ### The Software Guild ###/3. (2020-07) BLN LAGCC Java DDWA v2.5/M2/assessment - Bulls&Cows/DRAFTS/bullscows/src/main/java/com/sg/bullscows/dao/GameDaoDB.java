package com.sg.bullscows.dao;

import java.util.*;
import com.sg.bullscows.dto.*;
import org.springframework.web.bind.annotation.GetMapping;

public class GameDaoDB implements GameDao {

    @Override
    public Game beginGame() {
        throw new UnsupportedOperationException("ok");
    }

    @Override
    @GetMapping
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException("ok");
    }

    @Override
    @GetMapping("/{id}")
    public Game getGameByID(int gameID) {
        throw new UnsupportedOperationException("ok");
    }

}