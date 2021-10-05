package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Teacher;
import com.management.food.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher get(Long id) {
        return teacherRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

    public Page<Teacher> get(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    public Page<Teacher> get(String name, Pageable pageable) {
        return teacherRepository.findByNameContaining(name, pageable);
    }

}
