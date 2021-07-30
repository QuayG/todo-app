package de.neuefische.todobackend.repo;

import de.neuefische.todobackend.model.Todo;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository

public class TodoRepo {
    private List<Todo> todoList;

    public List<Todo> addTodo(String description){
        Todo newTodo = new Todo(description);
        todoList.add(newTodo);
        return todoList;

    }
    public List<Todo> removeTodo(Todo todoToRemove){
        todoList.remove(todoToRemove);
        return todoList;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }
}
