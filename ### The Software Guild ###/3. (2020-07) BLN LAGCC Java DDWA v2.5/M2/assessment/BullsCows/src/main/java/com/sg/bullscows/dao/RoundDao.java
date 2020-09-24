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
 * @author Music Account
 */
public interface RoundDao {
    Round guessRound();
    List<Round> getAllRounds();
    List<Round> getAllRoundsByGame(Game game);
}
