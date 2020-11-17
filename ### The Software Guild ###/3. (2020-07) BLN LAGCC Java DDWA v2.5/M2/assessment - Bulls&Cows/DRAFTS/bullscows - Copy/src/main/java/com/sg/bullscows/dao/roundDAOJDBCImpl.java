/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.dao;

import com.sg.bullscows.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class roundDAOJDBCImpl implements roundDAO {

    @Autowired
    JdbcTemplate JdbcTemplate;
    
    private final String beginRound = "insert into round (roundtime, guess, result, gameId) values (CURRENT_TIMESTAMP, ?, ?, ?)";
    private final String getRoundByGameId = "select * from round where gameId = ?";


    @Override
    public List<round> getRoundByGameId(int id) {
        return JdbcTemplate.query(getRoundByGameId, new roundMapper(), id);
    }

    @Override
    public round beginRound(round newround) {
        JdbcTemplate.update(beginRound, newround.getGuess(), newround.getResult(), newround.getGameId() );
        int id = JdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class); //returns id of recently added object from sql 
        newround.setRoundId(id);
        return newround;
        }
    
 private class roundMapper implements org.springframework.jdbc.core.RowMapper<round> {

        @Override
        public round mapRow(ResultSet rs, int i) throws SQLException {
        round newround = new round();
        newround.setRoundId(rs.getInt("roundId"));
//        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        newround.setRoundtime(LocalDateTime.parse((CharSequence) rs.getDate("roundtime"), customFormatter));
        newround.setGameId(rs.getInt("gameId"));
        newround.setGuess(rs.getString("guess"));
        newround.setResult(rs.getString("result"));
        return newround;
        }
        
    } ;
}

