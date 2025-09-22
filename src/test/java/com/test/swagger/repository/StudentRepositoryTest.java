package com.test.swagger.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.swagger.entity.Student;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveStudent() {
        Student s = new Student(null, "test", 22, "test@gmail.com", "test123");
        Student savedStudent = studentRepository.save(s);

        assert (savedStudent.getId() != null);
    }
}
