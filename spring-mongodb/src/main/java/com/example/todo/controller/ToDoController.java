package com.example.todo.controller;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.exception.ToDoCollectionException;
import com.example.todo.repo.ToDoRepository;
import com.example.todo.service.ToDoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository todoRepo;

    @Autowired
    private ToDoService todoService;

    @GetMapping("/getToDo")
    public ResponseEntity<?> getAllToDo(){
        List<ToDoDTO> toDoList = todoService.getAllToDo();
        return new ResponseEntity<>(toDoList, !toDoList.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addTodo")
    public ResponseEntity<?> createToDo(@RequestBody ToDoDTO todoDTO){
        try {
            todoService.createToDo(todoDTO);
            return new ResponseEntity<ToDoDTO>(todoDTO, HttpStatus.CREATED);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (ToDoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getSingleToDo/{id}")
    public ResponseEntity<?> getSingleToDo(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(todoService.getSingleToDoById(id), HttpStatus.OK);
        }catch (ToDoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/updateSingleToDo/{id}")
    public ResponseEntity<?> updateSingleToDo(@PathVariable("id") String id, @RequestBody ToDoDTO todoDTO){
        try {
            todoService.updateTodo(id, todoDTO);
            return new ResponseEntity<>("ToDo Updated Successfully", HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ToDoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deleteSingleToDo/{id}")
    public ResponseEntity<?> deleteSingleToDo(@PathVariable("id") String id){
        try {
            todoService.deleteToDoById(id);
            return new ResponseEntity<>("ToDo Deleted Successfully", HttpStatus.OK);
        }catch (ToDoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
