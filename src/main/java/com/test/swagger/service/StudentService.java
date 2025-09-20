package com.test.swagger.service;

import java.util.List;

import com.test.swagger.dto.StudentDto;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto addStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    void deleteStudent(Long id);
}
