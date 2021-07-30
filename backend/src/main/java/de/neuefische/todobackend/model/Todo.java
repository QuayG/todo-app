package de.neuefische.todobackend.model;

import jdk.jshell.Snippet;

import java.util.Objects;
import java.util.UUID;

public class Todo {
    private final String id;
    private final String description;
    private TodoStatus status;

    public Todo(String description, TodoStatus status) {
        this.description = description;
        this.status = status;
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(description, todo.description) && status == todo.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TodoStatus getStatus() {
        return status;
    }
}
