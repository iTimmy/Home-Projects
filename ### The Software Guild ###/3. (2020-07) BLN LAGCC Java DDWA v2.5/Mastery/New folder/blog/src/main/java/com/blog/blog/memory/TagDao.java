package com.blog.blog.memory;

import java.util.List;
import com.blog.blog.models.*;

public interface TagDao {
    List<Tag> getAllTags();
    Tag createTags(String tagName);
    Tag getTagByName(String title); 
    void updateTags(Tag tags);
    void deleteTags(Tag tags);
}
