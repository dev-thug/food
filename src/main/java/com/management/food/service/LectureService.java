package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.dto.LectureDTO;
import com.management.food.entity.Food;
import com.management.food.entity.Lecture;
import com.management.food.entity.Teacher;
import com.management.food.repository.FoodRepository;
import com.management.food.repository.LectureRepository;
import com.management.food.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final FoodRepository foodRepository;
    private final TeacherRepository teacherRepository;

    public Page<Lecture> get(Pageable pageable) {
        return lectureRepository.findAll(pageable);
    }

    public Lecture get(Long id) {
        return lectureRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

    public Page<Lecture> get(String name, Pageable pageable) {
        return lectureRepository.findByNameContaining(name, pageable);
    }

    public Lecture add(LectureDTO lectureDTO) {
        Food food = foodRepository.getById(lectureDTO.getFoodId());
        Teacher teacher = teacherRepository.getById(lectureDTO.getTeacherId());
        Lecture lecture = Lecture.builder().name(lectureDTO.getName())
                .dateAt(LocalDate.parse(lectureDTO.getDate()))
                .fromAt(LocalTime.parse(lectureDTO.getFromAt()))
                .time(lectureDTO.getTime())
                .food(food)
                .teacher(teacher)
                .build();
        return lectureRepository.save(lecture);
    }

    public Lecture add(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public void remove(Long id) {
        lectureRepository.delete(get(id));
    }
}
