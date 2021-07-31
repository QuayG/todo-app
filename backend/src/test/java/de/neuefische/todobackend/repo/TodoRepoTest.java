package de.neuefische.todobackend.repo;

import de.neuefische.todobackend.model.Todo;
import de.neuefische.todobackend.model.TodoStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoRepoTest {

    @Test
    @DisplayName("getTodoList method should return a list of all todos.")
    public void testGetTodoList(){

        //WHEN
        Todo testing = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");
        Todo refactoring = new Todo("Refactoring", TodoStatus.OPEN, "2");
        List<Todo> expected = List.of(testing,refactoring);
        Todo todoMock = Mockito.mock(Todo.class);

        //WHEN
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(testing);
        todoRepo.addTodo(refactoring);
        List<Todo> actual = todoRepo.getTodoList();

        //THEN
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("advanceStatus method should return todo with updated status.")
    public void testAdvanceStatus(){

        //GIVEN
        Todo todoToAdvance = new Todo("Testing", TodoStatus.OPEN, "1");
        Todo expected = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");

        //WHEN
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(todoToAdvance);
        todoRepo.advanceStatus(expected).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Todo actual = todoRepo.getTodoById("1").orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        //THEN
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("advanceStatus method should throw exception when called with non existing todo.")
    public void testAdvanceStatusWithNonexistingTodo(){

        //GIVEN
        Todo todoToAdvance = new Todo("Testing", TodoStatus.OPEN, "1");
        TodoRepo todoRepo = new TodoRepo();

        try {
            //WHEN
            todoRepo.advanceStatus(todoToAdvance).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            Todo actual = todoRepo.getTodoById("1").orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (ResponseStatusException e){
            //THEN
            assertEquals("404 NOT_FOUND", e.getMessage());
        }
    }

    @Test
    @DisplayName("removeTodo method should return list of todos without the removed todo.")
    public void testRemoveTodo(){

        //GIVEN
        TodoRepo todoRepo = new TodoRepo();
        Todo freitagsaufgabe = new Todo("Freitagsaufgabe", TodoStatus.DONE, "1");
        Todo testing = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");
        Todo refactoring = new Todo("Refactoring", TodoStatus.OPEN, "2");

        todoRepo.addTodo(freitagsaufgabe);
        todoRepo.addTodo(testing);
        todoRepo.addTodo(refactoring);

        //WHEN
        List<Todo> actual = todoRepo.removeTodo(freitagsaufgabe);
        List<Todo> expected = List.of(testing,refactoring);

        //THEN
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("removeTodo method should throw exception when called with non existing todo.")
    public void testRemoveTodoWithNonexistingTodo(){

        //GIVEN
        TodoRepo todoRepo = new TodoRepo();
        Todo freitagsaufgabe = new Todo("Freitagsaufgabe", TodoStatus.DONE, "1");
        Todo testing = new Todo("Testing", TodoStatus.IN_PROGRESS, "1");
        Todo refactoring = new Todo("Refactoring", TodoStatus.OPEN, "2");

        todoRepo.addTodo(testing);
        todoRepo.addTodo(refactoring);

        try {
            //WHEN
            List<Todo> actual = todoRepo.removeTodo(freitagsaufgabe);
            List<Todo> expected = List.of(testing,refactoring);
        } catch (ResponseStatusException e){
            //THEN
            assertEquals("404 NOT_FOUND",e.getMessage());
        }
    }
}