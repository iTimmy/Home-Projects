package com.blog.blog.memory;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class BlogDaoTest {

    @Autowired
    BlogDao blogDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;

    static User userOne;
    static User userTwo;
    static User userThree;
    static Blog blogOne;
    static Blog blogTwo;
    static Blog blogThree;
    static Tag tagOne;
    static Tag tagTwo;
    static Tag tagThree;
    static Role roleOne;
    static Role roleTwo;
    static Role roleThree;

    public BlogDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Blog> blogs = blogDao.getAllBlogs();
        for (Blog b : blogs) {
            blogDao.deleteBlog(blogDao.getBlogByID(b.getBlogID()));
        }

        List<User> users = userDao.getAllUsers();
        for (User u : users) {
            userDao.deleteUser(userDao.getUserByID(u.getUserID()));
            ;
        }

        List<Role> roles = roleDao.getAllRoles();
        for (Role r : roles) {
            roleDao.deleteRole(r.getID());
            ;
        }

        List<Tag> tags = tagDao.getAllTags();
        for (Tag t : tags) {
            tagDao.deleteTags(tagDao.getTagByName(t.getName()));
        }

        /* USER */
        User u1 = new User("John Doe", "password");
        userOne = userDao.createUser(u1);
        User u2 = new User("John Smith", "passwordOne");
        userTwo = userDao.createUser(u2);
        User u3 = new User("John Don", "passwordComplication");
        userThree = userDao.createUser(u3);

        /* TAG(S) */
        Tag t1 = new Tag("fun");
        Tag t2 = new Tag("goner");
        Tag t3 = new Tag("sunny");

        /* BLOG */
        Blog b1 = new Blog("DiaryOne", "I am angry.");
        List<Tag> b1tags = new ArrayList<>();
        b1tags.add(t2);
        b1.setTags(b1tags);
        blogOne = blogDao.createBlog(b1);
        Blog b2 = new Blog("DiaryTwo", "I am happy.");
        List<Tag> b2tags = new ArrayList<>();
        b2tags.add(t1);
        b2tags.add(t3);
        b2.setTags(b2tags);
        blogTwo = blogDao.createBlog(b2);
        Blog b3 = new Blog("DiaryThree", "I am sad.");
        List<Tag> b3tags = new ArrayList<>();
        b3tags.add(t1);
        b3tags.add(t2);
        b3tags.add(t3);
        b3.setTags(b3tags);
        blogThree = blogDao.createBlog(b3);

        /* ROLE */
        Role r1 = new Role("ROLE_ADMIN");
        roleOne = roleDao.createRole(r1);
        Role r2 = new Role("ROLE_USER");
        roleTwo = roleDao.createRole(r2);
        Role r3 = new Role("ROLE_CHILD");
        roleThree = roleDao.createRole(r3);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void test_createBlog() {
        // ARRANGE & ACT
        Blog blog1 = blogDao.createBlog(blogOne);
        Blog blogFromDao = blogDao.getBlogByID(blog1.getBlogID());
        // ASSERT
        assertNotNull(blog1);
        assertNotNull(blogFromDao);
        assertEquals(blog1, blogFromDao);
    }

    @Test
    public void test_updateBlog() {
        Blog blog1 = blogDao.createBlog(blogOne);
        Blog blogFromDao = blogDao.getBlogByID(blog1.getBlogID());

        userOne.setUsername(userTwo.getUsername());
        userOne.setPassword(userTwo.getPassword());
        blogDao.updateBlog(blogOne);
        Blog edit = blogDao.getBlogByID(blog1.getBlogID());

        assertNotNull(edit);
    }

    @Test
    public void test_getAllBlogs() {
        Blog blog1 = blogDao.createBlog(blogOne);
        Blog blog2 = blogDao.createBlog(blogTwo);
        Blog blog3 = blogDao.createBlog(blogThree);

        List<Blog> blogs = blogDao.getAllBlogs();

        assertTrue(blogs.contains(blog1));
        assertTrue(blogs.contains(blog2));
        assertTrue(blogs.contains(blog3));
        assertEquals(blogs.size(), 3);
    }

}