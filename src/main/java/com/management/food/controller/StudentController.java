package com.management.food.controller;

import com.management.food.dto.StudentDTO;
import com.management.food.entity.Student;
import com.management.food.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "수강생", description = "수강생 API")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "수강생 등록", description = "수강생 등록을 한다.")
    @PostMapping(value = "/student")
    public Student add(@ParameterObject @ModelAttribute StudentDTO studentDTO) {
        return studentService.add(studentDTO);
    }

}
