package com.example.todo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoForm {

    private Integer id;
    private String todo;
    private String detail;
    private Boolean isNew;
}
