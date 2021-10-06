package com.management.food.controller;

import com.management.food.dto.TeacherDTO;
import com.management.food.entity.Teacher;
import com.management.food.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "강사", description = "강사 API")
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @Operation(summary = "강사 등록", description = "새로운 강사를 등록합니다.")
    @PostMapping(value = "/teacher")
    public Teacher add(@ParameterObject @ModelAttribute TeacherDTO teacherDTO) {

        return teacherService.add(new Teacher(teacherDTO.getName(), teacherDTO.getAccount()));
    }

    @Operation(summary = "강사 조회", description = "검색과 페이징이 포함된 강사조회")
    @GetMapping(value = "/teacher")
    public Page<Teacher> get(@ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(required = false) String search) {
        if (search != null) {
            return teacherService.get(search, pageable);
        }
        return teacherService.get(pageable);
    }

    @Operation(summary = "강사 상세 조회", description = "강사 id로 상세조회")
    @GetMapping(value = "/teacher/{id}")
    public Teacher get(@PathVariable Long id) {
        return teacherService.get(id);
    }

}
