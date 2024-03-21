package com.example.todo.repo;

import com.example.todo.DTO.ToDoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends MongoRepository<ToDoDTO, String> {

    @Query("{'todo': ?0}")
    Optional<ToDoDTO> findByTodo(String todo);
}
