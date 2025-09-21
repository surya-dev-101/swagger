package com.test.swagger.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.swagger.dto.TodoDto;
import com.test.swagger.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo Management", description = "APIs for managing todo items")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Get all todo items", description = "Retrieve a list of all todo items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    @Operation(summary = "Create a new todo item", description = "Add a new todo item to the list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo item created successfully")
    })
    public ResponseEntity<TodoDto> createTodo(TodoDto todoDto) {
        TodoDto createdTodo = todoService.createTodo(todoDto);
        return ResponseEntity.ok(createdTodo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a todo item", description = "Update an existing todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo item updated successfully"),
            @ApiResponse(responseCode = "404", description = "Todo item not found")
    })
    public ResponseEntity<TodoDto> updateTodo(Long id, TodoDto todoDto) {
        TodoDto updatedTodo = todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a todo item", description = "Delete a todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo item not found")
    })
    public ResponseEntity<Void> deleteTodo(Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

}
