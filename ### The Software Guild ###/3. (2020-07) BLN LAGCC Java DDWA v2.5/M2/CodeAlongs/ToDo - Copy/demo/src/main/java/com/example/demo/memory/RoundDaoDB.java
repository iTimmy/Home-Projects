package com.example.demo.memory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.models.Games;
import com.example.demo.models.Round;

@Repository
@Profile("database")
public class RoundDaoDB implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round beginRound(Round newRound, Games currentGame) {
        final String sql = "INSERT INTO Round(roundtime, guess, result, gameID) VALUES(CURRENT_TIMESTAMP, ?, ?, ?);";

        jdbcTemplate.update(sql, newRound.getGuess(), newRound.getResult(), newRound.getGameID());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        newRound.setRoundID(id);
        return newRound;
    }

    @Override
    public List<Round> getAllRoundsByGameID(int gameID) {
        final String sql = "SELECT * FROM Round WHERE gameID = ? ORDER BY roundtime ASC";
        return jdbcTemplate.query(sql, new ToDoMapper(), gameID);
    }

    @Override
    public boolean update(Round round) {
        final String sql = "UPDATE Round SET " + "guess = ?, " + "result = ? " + "WHERE roundID = ?;";
        return jdbcTemplate.update(sql, round.getGuess(), round.getResult(), round.getRoundID()) > 0;
    }

    private static final class ToDoMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round td = new Round();
            td.setGameID(rs.getInt("gameID"));
            td.setRoundID(rs.getInt("roundID"));
            td.setGuess(rs.getString("guess"));
            td.setResult(rs.getString("result"));
            td.setRoundtime(rs.getString("roundtime"));
            return td;
        }
    }
}