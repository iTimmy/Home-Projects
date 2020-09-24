package com.sg.bullscows.dao;

import java.util.*;
import com.sg.bullscows.dto.*;

public interface RoundDao {

    Round guessRound();
    List<Round> getAllRounds();
    List<Round> getAllRoundsByGame(Game game);
    
}