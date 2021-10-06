package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.dto.StudentDTO;
import com.management.food.entity.Lecture;
import com.management.food.entity.Student;
import com.management.food.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student add(StudentDTO studentDTO) {
        return add(new Student(studentDTO.getName(), studentDTO.getPhone()));
    }

    public Student get(Long id) {
        return studentRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

}
