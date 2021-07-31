package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.Todo;
import de.neuefische.todobackend.model.TodoStatus;
import de.neuefische.todobackend.repo.TodoRepo;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    @Test
    @DisplayName("addTodo method should add todo to list of todos in the todoRepo.")
    public void testAddTodo(){

        //GIVEN
        Todo freitagsaufgabe = new Todo("Freitagsaufgabe", TodoStatus.DONE, "0");
        Todo testing = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");
        TodoRepo todoRepo = new TodoRepo();

        //WHEN
        todoRepo.addTodo(freitagsaufgabe);
        todoRepo.addTodo(testing);
        List<Todo> expected = List.of(freitagsaufgabe,testing);
        List<Todo> actual = todoRepo.getTodoList();

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("removeTodo method should return the list of all todos from the todoRepo after " +
            "removing the passed in todo.")
    public void testRemoveTodo(){

        //GIVEN
        Todo freitagsaufgabe = new Todo("Freitagsaufgabe", TodoStatus.DONE, "0");
        Todo testing = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");
        Todo refactoring = new Todo("Refactoring", TodoStatus.OPEN, "2");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(freitagsaufgabe);
        todoRepo.addTodo(testing);
        todoRepo.addTodo(refactoring);

        //WHEN
        List<Todo> expected = List.of(testing, refactoring);
        List<Todo> actual = todoRepo.removeTodo(freitagsaufgabe);

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("removeTodo method should throw exception if called with non existing todo.")
    public void testRemoveTodoWithNonexistingTodo(){

        //GIVEN
        Todo freitagsaufgabe = new Todo("Freitagsaufgabe", TodoStatus.DONE, "0");
        Todo testing = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");
        Todo refactoring = new Todo("Refactoring", TodoStatus.OPEN, "2");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(testing);
        todoRepo.addTodo(refactoring);

        try {
            //WHEN
            List<Todo> expected = List.of(testing, refactoring);
            List<Todo> actual = todoRepo.removeTodo(freitagsaufgabe);
        } catch (ResponseStatusException e){
            //THEN
            assertEquals("404 NOT_FOUND", e.getMessage());
        }
    }
}