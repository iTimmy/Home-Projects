package com.sg.bullscows.dao;

import com.sg.bullscows.dto.*;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PTGIN
 */
public interface gameDAO {
    
    List<game> getAllGames();
    
    game getGameById(int id);
    
    int beginGame(game newgame);
    
    void updateIsFinishedoTrue(int gameID);
    
}
