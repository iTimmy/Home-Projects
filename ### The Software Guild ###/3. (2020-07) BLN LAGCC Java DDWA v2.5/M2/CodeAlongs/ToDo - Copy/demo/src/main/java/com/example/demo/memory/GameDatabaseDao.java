package com.example.demo.memory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.models.Games;

@Repository
@Profile("database")
public class GameDatabaseDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int beginGame(Games newGame) {
        final String sql = "INSERT INTO Game(answers, isFinished) VALUES(?, ?);";

        jdbcTemplate.update(sql, newGame.getAnswers(), false);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    @Override
    public List<Games> getAllGames() {
        final String sql = "SELECT gameID, answers, isFinished FROM Game;";
        return jdbcTemplate.query(sql, new ToDoMapper());
    }

    @Override
    public Games getGameByID(int id) {
        final String sql = "SELECT gameID, answers, isFinished " + "FROM Game WHERE gameID = ?;";
        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }

    @Override
    public void update(int gameID) {
        final String sql = "UPDATE Game SET isFinished = true " + 
            "WHERE gameID = ?;";
        jdbcTemplate.update(sql, gameID);
    }



    private static final class ToDoMapper implements RowMapper<Games> {
        @Override
        public Games mapRow(ResultSet rs, int index) throws SQLException {
            Games td = new Games();
            td.setGameID(rs.getInt("gameID"));
            td.setAnswers(rs.getString("answers"));
            td.setFinished(rs.getBoolean("isFinished"));
            return td;
        }
    }
}