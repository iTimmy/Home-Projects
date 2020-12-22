package com.example.demo.memory;

import java.util.*;

import com.example.demo.models.*;
/**
 *package com.example.demo.memory;
 
 public class RoundDao {
     
 }
 
 * @author Music Account
 */
public interface RoundDao {

    Round beginRound(Round newRound, Games currentGame);
    List<Round> getAllRoundsByGameID(int gameID);
    boolean update(Round round);

}
