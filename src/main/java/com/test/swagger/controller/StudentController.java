package com.test.swagger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.swagger.dto.StudentDto;
import com.test.swagger.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students data")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello") 
    public String hello(HttpServletRequest request) {
        return "Hello World" + request.getSession().getId();
    }

    @Operation(summary = "Add a new student", description = "Creates a new student record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(
            @Parameter(description = "Student object to be added", required = true) @RequestBody StudentDto studentDto) {
        StudentDto addedStudent = studentService.addStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
    }

    @Operation(summary = "Get student by ID", description = "Retrieves a student record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")

    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(
            @Parameter(description = "Student Id", required = true) @PathVariable("id") Long id) {
        StudentDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Get all students", description = "Retrieves all student records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    })
    @GetMapping("")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Update student", description = "Updates an existing student record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @Parameter(description = "Student Id", required = true) @PathVariable("id") Long id,
            @Parameter(description = "Updated student object", required = true) @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Delete student", description = "Deletes an existing student record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "Student Id", required = true) @PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
