package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Lecture;
import com.management.food.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public Page<Lecture> get(Pageable pageable) {
        return lectureRepository.findAll(pageable);
    }

    public Lecture get(Long id) {
        return lectureRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

    public Page<Lecture> get(String name, Pageable pageable) {
        return lectureRepository.findByNameContaining(name, pageable);
    }

    public Lecture add(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public void remove(Long id) {
        lectureRepository.delete(get(id));
    }
}
