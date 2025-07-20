package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.todo.entity.ToDo;
import com.example.todo.repository.ToDoMapper;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args)
		.getBean(TodoApplication.class).exe();
	}

	private final ToDoMapper mapper;

    public void exe() {
        System.out.println("=== 全件検索 ===");
        for (ToDo row : mapper.selectAll()) {
            System.out.println(row);
        }

        System.out.println("=== １件検索 ===");
        System.out.println(mapper.selectById(1));

        ToDo todo = new ToDo();
        todo.setTodo("登録テスト");
        todo.setDetail("登録処理");
        mapper.insert(todo);
        System.out.println("=== 登録確認 ===");
        System.out.println(mapper.selectById(4));

        ToDo target = mapper.selectById(4);
        target.setTodo("更新テスト");
        target.setDetail("更新処理");        
        mapper.update(target);
        System.out.println("=== 更新確認 ===");
        System.out.println(mapper.selectById(4));

        mapper.delete(4);
        System.out.println("=== 削除確認 ===");
        for (ToDo row : mapper.selectAll()) {
            System.out.println(row);
        }        
    }
}
