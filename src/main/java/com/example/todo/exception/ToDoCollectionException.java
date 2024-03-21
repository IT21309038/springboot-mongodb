package com.example.todo.exception;

public class ToDoCollectionException extends Exception{

    private static final long serialVersionUID = 1L;

    public ToDoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Todo with " + id + " not found";
    }

    public static String AlreadyExists() {
        return "Todo with given name already exists";
    }
}
