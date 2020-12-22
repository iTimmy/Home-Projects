package com.blog.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TagController {

    @Autowired
    TagDao dao;
    @Autowired
    BlogDao blogDao;
    private static Logger logger = LoggerFactory.getLogger(TagController.class);



    @GetMapping("tags")
    public String getAllTags(Model model) {
        List<Tag> tags = dao.getAllTags();
        model.addAttribute("tags", tags);
        return "tags";
    }

    // @PostMapping("createTag")
    // public String createTags(HttpServletRequest request) {
    //     Tags newTag = new Tags();
    //     newTag.setName(request.getParameter("name"));

    //     // dao.createTags(newTag);
    //     return "redirect:/writeBlog";
    // }

    @GetMapping("tags/{tagsID}")
    public String getTags(@PathVariable String name) {
        dao.getTagByName(name);
        return "redirect:/tags";
    }

    @PostMapping("tags/{tagsID}")
    public String updateTags(Tag tags) {
        dao.updateTags(tags);
        return "redirect:/tags";
    }

    @PostMapping("deleteTags")
    public String deleteTags(HttpServletRequest request) {
        logger.info(request.getParameter("delete-tags"));
        String[] tagNames = (request.getParameter("name")).split(",");
        for (String name : tagNames) {
            if (!name.equals("")) {
                dao.deleteTags(dao.getTagByName(name));
            }
        }
        return "redirect:/userManagement";
    }

}
