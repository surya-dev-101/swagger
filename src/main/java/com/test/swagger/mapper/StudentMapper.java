package com.test.swagger.mapper;

import org.mapstruct.Mapper;

import com.test.swagger.dto.StudentDto;
import com.test.swagger.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentDto dto);

    StudentDto toDto(Student entity);

}
