package com.blog.blog.memory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.blog.blog.models.*;
import com.blog.blog.memory.TagDaoDB.TagsMapper;
import com.blog.blog.memory.TagDaoDB.TagNamesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BlogDaoDB implements BlogDao {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static Logger logger = LoggerFactory.getLogger(BlogDaoDB.class);
    


    @Override
    public List<Blog> getAllBlogs() {
        final String sql = "SELECT * FROM Blogs;";
        List<Blog> blogs = jdbcTemplate.query(sql, new BlogsMapper());

        for (Blog blog : blogs) {
            final String SELECT_TAGS = "SELECT name, Tags.tagID FROM Tags " +
                "INNER JOIN BlogsTags on BlogsTags.tagID = Tags.tagID " +
                "WHERE BlogsTags.blogID = ?;";
            List<Tag> tags = jdbcTemplate.query(SELECT_TAGS, new TagsMapper(), blog.getBlogID());
            blog.setTags(tags);
        }
        return blogs;
    }

    private boolean doesTitleExist(String title) {
        String FIND_TITLE = "SELECT COUNT(*) FROM Blogs WHERE title = ?;";
        int status = jdbcTemplate.queryForObject(FIND_TITLE,
            new Object[] { title }, Integer.class);
        return status >= 1 ? true : false;
    }

    @Override
    @Transactional
    public Blog createBlog(Blog newBlog) {    
        if (doesTitleExist(newBlog.getTitle()) == true) {
            return null;
        }

        logger.info("POST DATE: " + newBlog.getPostDate() + " | " + "EXPIRATION DATE: " + newBlog.getExpirationDate());
        logger.info(newBlog.getPhoto());

        final String INSERT_BLOG = "INSERT INTO Blogs(title, date, content, userID, approved, postDate, expirationDate, photo) VALUES(?, CURRENT_TIMESTAMP, ?, ?, false, ?, ?, ?);";
        jdbcTemplate.update(INSERT_BLOG, 
            newBlog.getTitle(),
            newBlog.getContent(),
            newBlog.getUserID(),
            newBlog.getPostDate(),
            newBlog.getExpirationDate(),
            "assets\\" + newBlog.getPhoto());
        int newBlogID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        newBlog.setBlogID(newBlogID);

        List<Tag> tags = newBlog.getTags();

        for (Tag tag : tags) {
            final String INSERT_BLOGS_TAGS = "INSERT INTO BlogsTags(blogID, tagID) VALUES(?, ?);";
            jdbcTemplate.update(INSERT_BLOGS_TAGS,
                newBlog.getBlogID(),
                tag.getTagID());
        }

        return newBlog;
    }

    @Override
    public Blog getBlogByTitle(String title) {
        final String SELECT_BLOG = "SELECT * FROM Blogs WHERE title = ?;";
        Blog blog = jdbcTemplate.queryForObject(SELECT_BLOG, new BlogsMapper(), title);
        blog.setTitle(title);

        final String SELECT_TAGS = "SELECT name, Tags.tagID FROM Tags " +
            "INNER JOIN BlogsTags on BlogsTags.tagID = Tags.tagID " +
            "WHERE BlogsTags.blogID = ?;";
        List<Tag> tags = jdbcTemplate.query(SELECT_TAGS, new TagsMapper(), blog.getBlogID());

        blog.setTags(tags);

        return blog;
    }

    @Override
    public Blog getBlogByID(int blogID) {
        final String sql = "SELECT * FROM Blogs WHERE blogID = ?;";
        Blog blog = jdbcTemplate.queryForObject(sql, new BlogsMapper(), blogID);
        blog.setBlogID(blogID);

        final String SELECT_TAGS = "SELECT name, Tags.tagID FROM Tags "
                + "INNER JOIN BlogsTags on BlogsTags.tagID = Tags.tagID " + "WHERE BlogsTags.blogID = ?;";
        List<Tag> tags = jdbcTemplate.query(SELECT_TAGS, new TagsMapper(), blog.getBlogID());

        blog.setTags(tags);

        return blog;
    }

    @Override
    public boolean updateBlog(Blog blog) {
        if (doesTitleExist(blog.getTitle()) == true) {
            return false;
        }
        
        final String sql = "UPDATE Blogs " +
            "SET title = ?, date = CURRENT_TIMESTAMP, content = ?, approved = ?, photo = ? " +
            "WHERE blogID = ?;";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.isApproved(), blog.getPhoto(), blog.getBlogID());

        List<Tag> tags = blog.getTags();

        final String DELETE = "DELETE FROM BlogsTags WHERE blogID = ?;";
        jdbcTemplate.update(DELETE, blog.getBlogID());

        for (Tag tag : tags) {
            final String REPLACE_BLOGS_TAGS = "INSERT INTO BlogsTags(blogID, tagID) VALUES(?, ?);";
            jdbcTemplate.update(REPLACE_BLOGS_TAGS,
                blog.getBlogID(),
                tag.getTagID());
        }

        return true;
    }

    @Override
    public void deleteBlog(Blog blog) {
        final String FIRST_DELETE = "DELETE FROM BlogsTags WHERE blogID = ?;";
        jdbcTemplate.update(FIRST_DELETE, blog.getBlogID());
        final String SECOND_DELETE = "DELETE FROM Blogs WHERE blogID = ?;";
        jdbcTemplate.update(SECOND_DELETE, blog.getBlogID());
    }



    private static final class BlogsMapper implements RowMapper<Blog> {
        @Override
        public Blog mapRow(ResultSet rs, int index) throws SQLException {
            Blog blogs = new Blog();
            blogs.setBlogID(rs.getInt("blogID"));
            blogs.setUserID(rs.getInt("userID"));
            blogs.setTitle(rs.getString("title"));
            blogs.setDate(rs.getDate("date").toLocalDate());
            blogs.setContent(rs.getString("content"));
            blogs.setApproved(rs.getBoolean("approved"));
            if (rs.getDate("postDate") != null) {blogs.setPostDate(rs.getDate("postDate").toLocalDate());} 
            if (rs.getDate("expirationDate") != null) {blogs.setExpirationDate(rs.getDate("expirationDate").toLocalDate());} 
            blogs.setPhoto(rs.getString("photo"));
            return blogs;
        }
    }

}
