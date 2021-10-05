package com.management.food.controller;

import com.management.food.dto.LectureDTO;
import com.management.food.entity.Lecture;
import com.management.food.repository.LectureRepository;
import com.management.food.service.FoodService;
import com.management.food.service.LectureService;
import com.management.food.service.TeacherService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "강의", description = "강의 API")
@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final TeacherService teacherService;
    private final FoodService foodService;

    @GetMapping(value = "/lecture")
    public Page<Lecture> get(@ParameterObject @PageableDefault(sort = {"dateAt", "fromAt"}) Pageable pageable) {
        return lectureService.get(pageable);
    }

    @GetMapping(value = "/lecture/{id}")
    public Lecture get(@PathVariable Long id) {
        return lectureService.get(id);
    }

    @PostMapping(value = "/lecture")
    public Lecture add(@ParameterObject @ModelAttribute LectureDTO lectureDTO) {


        return lectureService.add(lectureDTO);
    }


}
