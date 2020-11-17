package com.bullscows.bullscows.service;

import java.util.*;
import com.bullscows.bullscows.dto.*;

public interface GameService {
    void beginGame();
    List<Game> getAllGames();
    Game getGameByID(int gameID);

    Round guessRound();
    List<Round> getAllRounds();
    List<Round> getAllRoundsByGame(Game game);
}
