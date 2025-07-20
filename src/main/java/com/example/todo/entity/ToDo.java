package com.example.todo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {

	private Integer id;
    private String todo;
    private String detail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}