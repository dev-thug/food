package com.management.food.repository;

import com.management.food.entity.Application;
import com.management.food.entity.Lecture;
import com.management.food.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findAllByUser(User user, Pageable pageable);

    Page<Application> findAllByLecture(Lecture lecture, Pageable pageable);

    Page<Application> findAllByLectureAndStatus(Lecture lecture, Application.Status status, Pageable pageable);
}
