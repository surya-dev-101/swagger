package com.test.swagger.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.swagger.dto.TodoDto;
import com.test.swagger.exception.TodoNotFoundException;
import com.test.swagger.mapper.TodoMapper;
import com.test.swagger.repository.TodoRepository;
import com.test.swagger.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoMapper todoMapper, TodoRepository todoRepository) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        return todoMapper.toDto(
                todoRepository.save(
                        todoMapper.toEntity(todoDto)));
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toDto)
                .toList();
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(todoDto.getTitle());
                    todo.setCompleted(todoDto.isCompleted());
                    return todoRepository.save(todo);
                })
                .map(todoMapper::toDto)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}
