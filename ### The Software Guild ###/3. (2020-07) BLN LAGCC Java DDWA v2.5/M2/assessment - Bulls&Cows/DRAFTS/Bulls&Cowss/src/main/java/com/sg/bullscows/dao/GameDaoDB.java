package com.sg.bullscows.dao;

import java.util.*;
import com.sg.bullscows.dto.*;
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

public class GameDaoDB implements GameDao {

    @GetMapping("/{id}")
    @Override
    public Game beginGame() {

    }

    @Override
    public List<Game> getAllGames() {

    }

    @Override
    public Game getGameByID(int gameID) {

    }

}