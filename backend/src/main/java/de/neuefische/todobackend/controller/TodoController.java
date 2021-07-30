package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.Todo;
import de.neuefische.todobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public List<Todo> addTodo(@RequestBody Todo newTodo) {
        return todoService.addTodo(newTodo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getTodoRepo().getTodoList();
    }

}
