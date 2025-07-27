package com.example.todo.service;

import java.util.List;

import com.example.todo.entity.ToDo;

public interface ToDoService {

    List<ToDo> findAllToDo();

    ToDo findByIdToDo(Integer id);

    void insertToDo(ToDo toDo);

    void updateToDo(ToDo toDo);

    void deleteToDo(Integer id);
}
