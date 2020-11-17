package com.example.demo.memory;

import java.util.List;
import com.example.demo.models.ToDo;

import org.springframework.stereotype.Repository;

public interface ToDoDao {

    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo findById(int id);

    // true if item exists and is updated
    boolean update(ToDo todo);

    // true if item exists and is deleted
    boolean deleteById(int id);
}