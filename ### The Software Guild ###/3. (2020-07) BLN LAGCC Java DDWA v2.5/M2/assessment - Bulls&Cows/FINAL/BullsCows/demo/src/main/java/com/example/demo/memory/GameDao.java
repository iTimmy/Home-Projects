package com.example.demo.memory;

import java.util.List;
import com.example.demo.models.Games;

import org.springframework.stereotype.Repository;

public interface GameDao {

    int beginGame(Games newGame);
    List<Games> getAllGames();
    Games getGameByID(int id);
    void update(int gameID);
    
}