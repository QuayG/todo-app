package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.Todo;
import de.neuefische.todobackend.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> addTodo(Todo newTodo) {
        todoRepo.addTodo(newTodo);
        return todoRepo.getTodoList();
    }

    public List<Todo> removeTodo(Todo todoToRemove) {
        todoRepo.removeTodo(todoToRemove);
        return todoRepo.getTodoList();
    }

    public TodoRepo getTodoRepo() {
        return todoRepo;
    }

    public Todo advanceStatus(Todo updatedTodo) {
        return todoRepo.advanceStatus(updatedTodo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Todo deleteTodo(String id) {
        return todoRepo.deleteTodo(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
