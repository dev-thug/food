package com.management.food.controller;

import com.management.food.dto.LectureDTO;
import com.management.food.entity.Lecture;
import com.management.food.service.FoodService;
import com.management.food.service.LectureService;
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

@Tag(name = "강의", description = "강의 API")
@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final TeacherService teacherService;
    private final FoodService foodService;

    @Operation(summary = "강의 조회", description = "개설된 강의를 조회합니다..")
    @GetMapping(value = "/lecture")
    public Page<Lecture> get(@ParameterObject @PageableDefault(direction = Sort.Direction.DESC, sort = {"dateAt", "fromAt"}) Pageable pageable) {
        return lectureService.get(pageable);
    }

    @Operation(summary = "강의 상세 조회", description = "개설된 강의를 상세 조회합니다.")
    @GetMapping(value = "/lecture/{id}")
    public Lecture get(@PathVariable Long id) {
        return lectureService.get(id);
    }

    @Operation(summary = "강의 등록", description = "새로운 강의를 등록합니다.")
    @PostMapping(value = "/lecture")
    public Lecture add(@ParameterObject @ModelAttribute LectureDTO lectureDTO) {
        return lectureService.add(lectureDTO);
    }

    @Operation(summary = "강의 수정", description = "강의를 수정합니다.")
    @PutMapping(value = "/lecture/{id}")
    public Lecture update(@PathVariable Long id, @ParameterObject @ModelAttribute LectureDTO lectureDTO) {
        return lectureService.update(id, lectureDTO);
    }


}
