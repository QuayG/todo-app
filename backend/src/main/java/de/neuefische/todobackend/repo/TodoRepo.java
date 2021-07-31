package de.neuefische.todobackend.repo;

import de.neuefische.todobackend.model.Todo;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public class TodoRepo {
    private List<Todo> todoList = new ArrayList<>();

    public Todo addTodo(Todo newTodo) {
        if (newTodo.getId() == null){
            String id = UUID.randomUUID().toString();
            Todo todo = new Todo(newTodo.getDescription(), newTodo.getStatus(), id);
            todoList.add(todo);
            return todo;
        }
        todoList.add(newTodo);
        return newTodo;
    }

    public Optional<Todo> advanceStatus(Todo updatedTodo) {
        for (Todo todo : todoList) {
            if (todo.getId().equals(updatedTodo.getId())) {
                todo.setStatus(updatedTodo.getStatus());
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }

    public List<Todo> removeTodo(Todo todoToRemove) {
        todoList.remove(todoToRemove);
        return todoList;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public Optional<Todo> deleteTodo(String id) {
        for (Todo todo : todoList) {
            if (todo.getId().equals(id)) {
                todoList.remove(todo);
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }

    public Optional<Todo> getTodoById(String id){
        for (Todo todo : todoList) {
            if (id.equals(todo.getId())){
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }
}
