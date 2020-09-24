package com.sg.bullscows.service;

import com.sg.bullscows.dto.*;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class GameServiceImpl implements GameService {
    @Override
    public void beginGame() {
        throw new UnsupportedOperationException(".");
    }

    @GetMapping("/{id}")
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException(".");
    }

    @GetMapping("/{id}")
    public Game getGameByID(Game game) {
        throw new UnsupportedOperationException(".");
    }
}
