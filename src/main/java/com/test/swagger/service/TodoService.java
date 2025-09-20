package com.test.swagger.service;

import java.util.List;

import com.test.swagger.dto.TodoDto;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(Long id);

    void deleteTodo(Long id);
}
