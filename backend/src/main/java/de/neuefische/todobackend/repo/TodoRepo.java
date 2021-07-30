package de.neuefische.todobackend.repo;

import de.neuefische.todobackend.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepo {
    private List<Todo> todoList;

    public TodoRepo(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
