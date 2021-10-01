package com.management.food.controller;

import com.management.food.entity.Teacher;
import com.management.food.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping(value = "/teacher")
    public Teacher add(@RequestBody Teacher teacher) {
        System.out.println(teacher.getName());
        return teacherService.add(teacher);
    }

    @GetMapping(value = "/teacher")
    public Page<Teacher> get(Pageable pageable) {
        return teacherService.get(pageable);
    }

    @GetMapping(value = "/teacher/{id}")
    public Teacher get(@PathVariable Long id) {
        return teacherService.get(id);
    }

}
