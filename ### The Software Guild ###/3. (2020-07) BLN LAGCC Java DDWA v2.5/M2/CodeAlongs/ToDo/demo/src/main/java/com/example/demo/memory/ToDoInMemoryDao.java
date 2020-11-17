package com.example.demo.memory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.models.ToDo;

@Repository
@Profile("memory")
public class ToDoInMemoryDao implements ToDoDao {

    private static final List<ToDo> todos = new ArrayList<>();

    @Override
    public ToDo add(ToDo todo) {
        int nextId = todos.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        todo.setId(nextId);
        todos.add(todo);

        return todo;
    }

    @Override
    public List<ToDo> getAll() {
        // List<ToDo> todos = new ArrayList<>();
        // ToDo todoOne = new ToDo();
        // todoOne.setId(1);
        // todoOne.setTodo("run");
        // todoOne.setNote("I'm fine");
        // todoOne.setFinished(false);
        // todos.add(todoOne);

        // ToDo todoTwo = new ToDo();
        // todoTwo.setId(1);
        // todoTwo.setTodo("run");
        // todoTwo.setNote("I'm fine");
        // todoTwo.setFinished(false);
        // todos.add(todoTwo);
        
        return new ArrayList<>(todos);
    }

    @Override
    public ToDo findById(int id) {
        return todos.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(ToDo todo) {

        int index = 0;
        while (index < todos.size()
                && todos.get(index).getId() != todo.getId()) {
            index++;
        }

        if (index < todos.size()) {
            todos.set(index, todo);
        }
        return index < todos.size();
    }

    @Override
    public boolean deleteById(int id) {
        return todos.removeIf(i -> i.getId() == id);
    }

}