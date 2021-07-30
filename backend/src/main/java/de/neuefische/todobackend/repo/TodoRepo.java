package de.neuefische.todobackend.repo;

import de.neuefische.todobackend.model.Todo;


import java.util.List;

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
