package com.test.swagger.mapper;

import org.mapstruct.Mapper;

import com.test.swagger.dto.TodoDto;
import com.test.swagger.entity.Todo;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoDto toDto(Todo todo);

    Todo toEntity(TodoDto todoDto);
}
