package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.dto.LectureDTO;
import com.management.food.entity.Food;
import com.management.food.entity.Lecture;
import com.management.food.repository.FoodRepository;
import com.management.food.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final FoodRepository foodRepository;
    private final UserService userService;

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
        Food food = foodRepository.findById(lectureDTO.getFoodId()).orElseThrow(NotFoundResourceException::new);

        Lecture lecture = Lecture.builder().name(lectureDTO.getName())
                .place(lectureDTO.getPlace())
                .dateAt(LocalDate.parse(lectureDTO.getDate()))
                .fromAt(LocalTime.parse(lectureDTO.getFromAt()))
                .time(lectureDTO.getTime())
                .food(food)
                .user(userService.getAuthedUser())
                .build();
        return lectureRepository.save(lecture);
    }

    public Lecture add(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public void remove(Long id) {
        lectureRepository.delete(get(id));
    }

    @Transactional
    public Lecture update(Long id, LectureDTO lectureDTO) {

        Lecture lecture = lectureRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        lecture.update(lectureDTO.getName(),
                lectureDTO.getPlace(),
                LocalDate.parse(lectureDTO.getDate()),
                LocalTime.parse(lectureDTO.getFromAt()),
                lectureDTO.getTime());

        return lectureRepository.save(lecture);
    }
}
