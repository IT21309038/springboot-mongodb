package com.example.todo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todo")
public class ToDoDTO {

    @Id
    private String id;

    @NotNull(message = "Todo cannot be null")
    private String todo;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Completed cannot be null")
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;


}
