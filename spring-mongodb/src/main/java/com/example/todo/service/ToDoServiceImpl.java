package com.example.todo.service;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.exception.ToDoCollectionException;
import com.example.todo.repo.ToDoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    private ToDoRepository todoRepo;


    @Override
    public void createToDo(ToDoDTO todo) throws ConstraintViolationException, ToDoCollectionException {
        Optional<ToDoDTO> todoOptional = todoRepo.findByTodo(todo.getTodo());
        if(todoOptional.isPresent()){
            throw new ToDoCollectionException(ToDoCollectionException.AlreadyExists());
        }else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }

    }

    @Override
    public List<ToDoDTO> getAllToDo() {
        List<ToDoDTO> todo = todoRepo.findAll();
        if (!todo.isEmpty()){
            return todo;
        }else {
            return new ArrayList<ToDoDTO>();
        }
    }

    @Override
    public ToDoDTO getSingleToDoById(String id) throws ToDoCollectionException {
        Optional<ToDoDTO> optTodo = todoRepo.findById(id);
        if(!optTodo.isPresent()){
            throw new ToDoCollectionException(ToDoCollectionException.NotFoundException(id));
        }else {
            return optTodo.get();
        }
    }

    @Override
    public void updateTodo(String id, ToDoDTO todo) throws ToDoCollectionException {
        Optional<ToDoDTO> todoWithId = todoRepo.findById(id);

        Optional<ToDoDTO> todoWithSameNaME = todoRepo.findByTodo(todo.getTodo());

        if(todoWithId.isPresent()){

            if(todoWithSameNaME.isPresent() && !todoWithSameNaME.get().getId().equals(id)){
                throw new ToDoCollectionException(ToDoCollectionException.AlreadyExists());
            }

            ToDoDTO todoUpdate = todoWithId.get();

            todoUpdate.setTodo(todo.getTodo());
            todoUpdate.setDescription(todo.getDescription());
            todoUpdate.setCompleted(todo.getCompleted());
            todoUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));

            todoRepo.save(todoUpdate);
        }else {
            throw new ToDoCollectionException(ToDoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteToDoById(String id) throws ToDoCollectionException {
        Optional<ToDoDTO> todo = todoRepo.findById(id);
        if(!todo.isPresent()){
            throw new ToDoCollectionException(ToDoCollectionException.NotFoundException(id));
        }else {
            todoRepo.deleteById(id);
        }
    }
}
