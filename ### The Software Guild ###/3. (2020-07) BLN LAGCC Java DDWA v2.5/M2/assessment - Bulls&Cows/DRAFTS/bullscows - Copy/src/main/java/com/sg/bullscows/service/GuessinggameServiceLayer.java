package com.sg.bullscows.service;

import com.sg.bullscows.dto.*;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PTGIN
 */

public interface GuessinggameServiceLayer {
    
        List<game> getAllGames();
    
    game getGameById(int id);
    
    int beginGame(game newgame);

    public List<round> getRoundGameById(int id);

    public round newround(round newround);
    
    
    
}
