package com.sg.bullscows.dao;

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
public interface roundDAO {
    
    List<round> getRoundByGameId(int id);
    
    round beginRound(round newround);
   
    }

