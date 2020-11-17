package com.bullscows.bullscows.service;

import java.util.*;
import com.bullscows.bullscows.dto.*;

public interface GameService {
    Game beginGame(Game newGame);
    List<Game> getAllGames();
    Game getGameByID(int gameID);

    Round addRound(Round newRound);
    List<Round> getAllRounds();
    List<Round> getAllRoundsByGame(Game game);
}
