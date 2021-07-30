package de.neuefische.todobackend.model;

public enum TodoStatus {
    DONE("Done"),
    IN_PROGRESS("Doing"),
    OPEN("Todo");

    public String status;

    TodoStatus(String status) {
        this.status = status;
    }
}
