package com.blog.blog.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.blog.blog.models.*;
import com.blog.blog.memory.TagDaoDB.TagsMapper;
import com.blog.blog.memory.TagDaoDB.TagNamesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProfileDaoDB implements ProfileDao {

    @Autowired
    JdbcTemplate jdbc;
    private static Logger logger = LoggerFactory.getLogger(ProfileDaoDB.class);

    

    @Override
    public Profile getProfileByID(int profileID) {
        final String SELECT_PROFILE = "SELECT * FROM Profile WHERE profileID = ?";
        return jdbc.queryForObject(SELECT_PROFILE, new ProfileMapper(), profileID);
    }

    @Override
    public void updateProfile(Profile profile) {
        jdbc.update(
            "UPDATE Profile SET icon = ?, " +
            "cover = ?, " +
            "wallpaper = ? " +
            "WHERE profileID = ?",
            profile.getIcon(), profile.getCover(), profile.getWallpaper(), profile.getProfileID());
    }



    public static final class ProfileMapper implements RowMapper<Profile> {
        @Override
        public Profile mapRow(ResultSet rs, int index) throws SQLException {
            return new Profile(rs.getInt("profileID"), rs.getString("icon"), rs.getString("cover"), rs.getString("wallpaper"));
        }
    }

}
