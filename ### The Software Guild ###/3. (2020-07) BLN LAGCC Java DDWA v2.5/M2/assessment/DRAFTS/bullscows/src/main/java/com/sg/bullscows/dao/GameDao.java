package com.sg.bullscows.dao;

import java.util.*;
import com.sg.bullscows.dto.*;

public interface GameDao {
    
    Game beginGame();
    List<Game> getAllGames();
    Game getGameByID(int gameID);
    
}