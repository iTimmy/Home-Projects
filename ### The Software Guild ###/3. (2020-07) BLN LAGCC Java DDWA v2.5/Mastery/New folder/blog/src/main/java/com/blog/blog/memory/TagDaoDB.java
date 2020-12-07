package com.blog.blog.memory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.blog.blog.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TagDaoDB implements TagDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static Logger logger = LoggerFactory.getLogger(TagDaoDB.class);
    

    
    @Override
    public List<Tag> getAllTags() {
        final String sql = "SELECT tagID, name FROM Tags;";
        return jdbcTemplate.query(sql, new TagsMapper());
    }

    private boolean doesTagExist(String tagName) {
        Boolean tagExists = false;

        final String SELECT_TAGS = "SELECT name FROM Tags";
        List<String> tagNames = jdbcTemplate.query(SELECT_TAGS, new TagNamesMapper());

        for (String name : tagNames) {
            if (tagName.equals(name)) {
                tagExists = true;
            }
        }

        return (tagExists == false) ? false : true;
    }

    @Override
    @Transactional
    public Tag createTags(String tagName) {
        Boolean tagExists = doesTagExist(tagName);
        if (tagExists == false) {
            Tag newTag = new Tag(tagName);
            final String sql = "INSERT INTO Tags(name) VALUES(?);";

            jdbcTemplate.update(sql,
                tagName);

            int newTagID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            newTag.setTagID(newTagID);
            return newTag;
        }
        return null;
    }

    @Override
    public Tag getTagByName(String name) {
        final String sql = "SELECT * FROM Tags WHERE name = ?;";
        return jdbcTemplate.queryForObject(sql, new TagsMapper(), name);
    }

    @Override
    public void updateTags(Tag tags) {
        final String sql = "UPDATE Tags SET " +
            "name = ? " + 
            "WHERE tagID = ?;";
        jdbcTemplate.update(sql, tags.getName(), tags.getTagID());
    }

    @Override
    public void deleteTags(Tag tags) {
        final String sql = "DELETE * " + "FROM Tags WHERE tagID = ?;";
        jdbcTemplate.update(sql, tags.getTagID());
    }



    public static final class TagsMapper implements RowMapper<Tag> {
        @Override
        public Tag mapRow(ResultSet rs, int index) throws SQLException {
            Tag tags = new Tag();
            tags.setTagID(rs.getInt("tagID"));
            tags.setName(rs.getString("name"));

            return tags;
        }
    }

    public static final class TagNamesMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int index) throws SQLException {
            return rs.getString("name");
        }
    }

}
