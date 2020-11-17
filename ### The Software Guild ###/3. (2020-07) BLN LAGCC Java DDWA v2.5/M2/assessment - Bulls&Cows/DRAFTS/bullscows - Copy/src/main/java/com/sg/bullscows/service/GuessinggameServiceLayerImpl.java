/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.service;

import com.sg.bullscows.dto.*;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GuessinggameServiceLayerImpl implements GuessinggameServiceLayer {

    @Autowired
    GameDao gamedao;
    
    @Autowired
    RoundDao rounddao;
    

    @Override
    public List<game> getAllGames() {
        List<game> games = gamedao.getAllGames();
        
        for (game currentgame: games){
            if (!currentgame.isIsFinished()) {
            currentgame.setAnswers("xxxx");
            }
                    
            
        }
        
        return games;
    }

    @Override
    public game getGameById(int id) {
        game gamefromdatabase =  gamedao.getGameById(id);
        if (gamefromdatabase == null) {
            return null;
        }
        
        
         if (!gamefromdatabase.isIsFinished()) {
                    gamefromdatabase.setAnswers("xxxx");
        }
        
        return gamefromdatabase;
    }

    @Override
    public int beginGame(game newgame) {
        newgame.setAnswers(getrandomnumber());
        return gamedao.beginGame(newgame);
    }

    
    
    private String getrandomnumber() {
        Random rand = new Random();
        String answer = "";

        while (answer.length() < 4) {
            int rand_int1 = rand.nextInt(10);
            if (!answer.contains(rand_int1 + "")) {
                answer = answer + rand_int1;
            }
        }
        return answer;
    }

    @Override
    public List<round> getRoundGameById(int id) {
        return rounddao.getRoundByGameId(id);
    }

    @Override
    public round newround(round newround) {

      game currentgame = gamedao.getGameById(newround.getGameId());

        if (currentgame.isIsFinished()) {
            return null;
        }
      
      String answer = currentgame.getAnswers();
      String guess = newround.getGuess();
      
       if (validateGuess(guess) == false) {
           return null;
       }
      
      
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < 4; i++) {
            if (answer.charAt(i) == guess.charAt(i)) {
                exact++;
            }

        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ( i != j) {
                    if (answer.charAt(i) == guess.charAt(j)) {
                        partial++;
                    }
                }
            }
        }
        
        if (exact == 4 ) { 
            gamedao.updateIsFinishedoTrue(newround.getGameId());
        }
     newround.setResult("p:" + partial+ ":e:" + exact);
    return rounddao.beginRound(newround);        
        
    }

    private boolean validateGuess(String guess) {
        if (guess.length() != 4) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ( i != j) {
                    if (guess.charAt(i) ==  guess.charAt(j)) {
                        return false; // if it finds duplicate return false
                    }
                }
            }
        }
        
        return true;
    }

}
