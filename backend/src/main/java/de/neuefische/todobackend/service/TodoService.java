package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.Todo;
import de.neuefische.todobackend.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> addTodo(String newTodoDescription) {
        todoRepo.addTodo(newTodoDescription);
        return todoRepo.getTodoList();
    }

    public List<Todo> removeTodo(Todo todoToRemove) {
        todoRepo.removeTodo(todoToRemove);
        return todoRepo.getTodoList();
    }
}
