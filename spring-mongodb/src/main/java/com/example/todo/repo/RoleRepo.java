package com.example.todo.repo;

import com.example.todo.DTO.RoleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepo extends MongoRepository<RoleDTO, Integer> {

    Optional<RoleDTO> findByName(String name);
}
