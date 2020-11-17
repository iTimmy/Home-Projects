/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.dao;

import com.sg.bullscows.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class gameDAOJDBCImpl implements gameDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private final String beginGame = "insert into game (answers, isFinished) values (?, ?)";
    private final String getAllGames = "select * from game";
    private final String getGameById = "select * from game where gameId = ?";
    private final String updateIsFinishedoTrue = "update game set isFinished = true where gameID = ?";
    
    @Override
    public List<game> getAllGames() {
        return jdbcTemplate.query(getAllGames, new gameMapper());
    }

    @Override
    public game getGameById(int id) {
        try {
        return jdbcTemplate.queryForObject(getGameById, new gameMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public int beginGame(game newgame) {
        jdbcTemplate.update(beginGame, newgame.getAnswers(), false);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class); //returns id of recently added object from sql 
    }

    @Override
    public void updateIsFinishedoTrue(int gameID) {
         jdbcTemplate.update(updateIsFinishedoTrue, gameID);
    }
    
    private class gameMapper implements org.springframework.jdbc.core.RowMapper<game> {

        @Override
        public game mapRow(ResultSet rs, int i) throws SQLException {
        game newgame = new game();
        newgame.setId(rs.getInt("gameId"));
        newgame.setAnswers(rs.getString("answers"));
        newgame.setIsFinished(rs.getBoolean("isFinished"));
        return newgame;
        }
    } ;
// missing 
}
