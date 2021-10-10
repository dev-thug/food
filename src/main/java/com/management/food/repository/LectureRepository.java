package com.management.food.repository;

import com.management.food.entity.Lecture;
import com.management.food.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Page<Lecture> findAllByNameContaining(String name, Pageable pageable);

}
