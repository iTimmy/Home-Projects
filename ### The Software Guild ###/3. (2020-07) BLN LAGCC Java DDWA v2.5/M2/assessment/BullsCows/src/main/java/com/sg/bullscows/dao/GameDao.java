/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.dao;

import java.util.*;
import com.sg.bullscows.dto.*;
/**
 *
 * @author timmy
 */
public interface GameDao {
    void beginGame();
    List<Game> getAllGames();
    Game getGameByID(Game game);
}
