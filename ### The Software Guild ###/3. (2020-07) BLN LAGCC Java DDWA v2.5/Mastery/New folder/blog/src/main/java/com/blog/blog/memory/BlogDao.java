package com.blog.blog.memory;

import java.util.List;
import com.blog.blog.models.*;

public interface BlogDao {
    List<Blog> getAllBlogs();
    Blog createBlog(Blog newBlog);
    Blog getBlogByTitle(String title); 
    Blog getBlogByID(int blogID);
    void updateBlog(Blog blog);
    void deleteBlog(Blog blog);
}
