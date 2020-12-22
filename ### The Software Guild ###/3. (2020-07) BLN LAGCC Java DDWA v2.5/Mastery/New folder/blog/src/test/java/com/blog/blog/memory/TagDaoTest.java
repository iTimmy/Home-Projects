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
public class TagDaoTest {

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

    public TagDaoTest() {
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
    public void test_createUser() {
        // ARRANGE & ACT
        Tag tag1 = tagDao.createTags(tagOne.getName());
        Tag tagFromDao = tagDao.getTagByName(tag1.getName());
        // ASSERT
        assertNotNull(tag1);
        assertNotNull(tagFromDao);
        assertEquals(tag1, tagFromDao);
    }

    @Test
    public void test_updateTags() {
        Tag tag1 = tagDao.createTags(tagOne.getName());
        Tag tagFromDao = tagDao.getTagByName(tag1.getName());

        tagOne.setName(tagTwo.getName());
        tagDao.updateTags(tagOne);
        Tag edit = tagDao.getTagByName(tag1.getName());

        assertNotNull(edit);
    }

    @Test
    public void test_getAllUsers() {
        Tag tag1 = tagDao.createTags(tagOne.getName());
        Tag tag2 = tagDao.createTags(tagTwo.getName());
        Tag tag3 = tagDao.createTags(tagThree.getName());

        List<Tag> tags = tagDao.getAllTags();

        assertTrue(tags.contains(tag1));
        assertTrue(tags.contains(tag2));
        assertTrue(tags.contains(tag3));
        assertEquals(tags.size(), 3);
    }

}