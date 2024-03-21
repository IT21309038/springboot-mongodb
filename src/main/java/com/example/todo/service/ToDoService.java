package com.example.todo.service;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.exception.ToDoCollectionException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ToDoService {

    public void createToDo(ToDoDTO todo) throws ConstraintViolationException, ToDoCollectionException;

    public List<ToDoDTO> getAllToDo();

    public ToDoDTO getSingleToDoById(String id) throws ToDoCollectionException;

    public void updateTodo(String id, ToDoDTO todo) throws ToDoCollectionException;

    public void deleteToDoById(String id) throws ToDoCollectionException;
}
