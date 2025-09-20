package com.test.swagger.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.test.swagger.dto.StudentDto;
import com.test.swagger.mapper.StudentMapper;
import com.test.swagger.repository.StudentRepository;
import com.test.swagger.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        return studentMapper.toDto(
                studentRepository.save(
                        studentMapper.toEntity(studentDto)));
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(studentDto.getName());
                    existingStudent.setAge(studentDto.getAge());
                    existingStudent.setEmail(studentDto.getEmail());
                    if (studentDto.getPassword() != null) {
                        existingStudent.setPassword(studentDto.getPassword());
                    }
                    return studentMapper.toDto(studentRepository.save(existingStudent));
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
