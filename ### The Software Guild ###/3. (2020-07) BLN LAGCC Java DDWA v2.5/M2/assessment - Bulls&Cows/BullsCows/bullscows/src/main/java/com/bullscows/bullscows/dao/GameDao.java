/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bullscows.bullscows.dao;

import java.util.*;
import com.bullscows.bullscows.dto.*;
/**
 *
 * @author timmy
 */
public interface GameDao {
    Game beginGame(Game newGame);
    List<Game> getAllGames();
    Game getGameByID(int gameID);
    //void updateGame();
}
