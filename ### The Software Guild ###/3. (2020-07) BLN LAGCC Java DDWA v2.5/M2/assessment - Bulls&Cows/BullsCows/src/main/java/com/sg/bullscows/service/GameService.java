package com.sg.bullscows.service;

import java.util.*;
import com.sg.bullscows.dto.*;

public interface GameService {
    void beginGame();
    List<Game> getAllGames();
    Game getGameByID(int gameID);

    Round guessRound();
    List<Round> getAllRounds();
    List<Round> getAllRoundsByGame(Game game);
}
