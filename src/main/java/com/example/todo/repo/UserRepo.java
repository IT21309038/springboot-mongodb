package com.example.todo.repo;

import com.example.todo.DTO.UserDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<UserDTO, ObjectId>{

    Optional<UserDTO> findByUsername(String username);
    Boolean existsByUsername(String username);
}
