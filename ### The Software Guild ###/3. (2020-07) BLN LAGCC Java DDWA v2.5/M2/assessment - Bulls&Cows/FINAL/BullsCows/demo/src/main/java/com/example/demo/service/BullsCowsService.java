package com.example.demo.service;

import java.util.List;
import com.example.demo.models.*;

public interface BullsCowsService {

    int beginGame(Games newGame);
    List<Games> getAllGames();
    Games getGameByID(int id);
    void update(int gameID);

    Round beginRound(Round newRound, Games currentGame);
    List<Round> getAllRoundsByGameID(int gameID);

}
