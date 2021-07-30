package de.neuefische.todobackend.repo;

import de.neuefische.todobackend.model.Todo;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
@Repository

public class TodoRepo {
    private List<Todo> todoList = new ArrayList<>();

    public Todo addTodo(Todo newTodo){
        Todo todo = new Todo(newTodo.getDescription(), newTodo.getStatus());
        todoList.add(todo);
        return todo;
    }

    public List<Todo> removeTodo(Todo todoToRemove){
        todoList.remove(todoToRemove);
        return todoList;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

}
