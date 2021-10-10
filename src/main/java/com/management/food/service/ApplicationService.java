package com.management.food.service;

import com.management.food.advice.exception.InsufficientPointException;
import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Application;
import com.management.food.entity.Lecture;
import com.management.food.entity.User;
import com.management.food.repository.ApplicationRepository;
import com.management.food.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final LectureRepository lectureRepository;
    private final UserService userService;

    public Page<Application> findByLecture(Long lectureId, Pageable pageable) {
        return findByLecture(lectureId, Application.Status.COMPLETED, pageable);
    }

    public Page<Application> findByLecture(Long lectureId, Application.Status status, Pageable pageable) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(NotFoundResourceException::new);
        return applicationRepository.findAllByLectureAndStatus(lecture, status, pageable);
    }

    public Page<Application> findByUser(Long userId, Pageable pageable) {
        User user = userService.get(userId);
        return applicationRepository.findAllByUser(user, pageable);
    }


    public Page<Application> findByUser(Pageable pageable) {
        User user = userService.getAuthedUser();
        return applicationRepository.findAllByUser(user, pageable);
    }

    public Application add(Application application) {
        return applicationRepository.save(application);
    }

    public Application add(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(NotFoundResourceException::new);

        Application application = new Application(userService.getAuthedUser(), lecture);
        return add(application);
    }

    public Application get(Long id) {
        return applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

    @Transactional
    public Application complete(Long id, int cost) {
        Application application = applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        User user = userService.getAuthedUser();
        if (!Objects.equals(user.getId(), application.getUser().getId())) {
            throw new AccessDeniedException("");
        }
        if (user.getPoint() < cost) {
            throw new InsufficientPointException();
        }

        if (application.getStatus() == Application.Status.IN_PROGRESS && application.getLecture().getFood().getCost() == cost) {
            user.setPoint(user.getPoint() - cost);
            application.complete(cost);
        }
        return application;
    }

    @Transactional
    public Application refund(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        User user = userService.getAuthedUser();
        if (!Objects.equals(user.getId(), application.getUser().getId())) {
            throw new AccessDeniedException("");
        }

        if (application.getStatus() == Application.Status.COMPLETED) {
            user.setPoint(user.getPoint() + application.getCost());
            application.refund();
        }
        return application;
    }

    @Transactional
    public Application cancel(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        User user = userService.getAuthedUser();
        if (!Objects.equals(user.getId(), application.getUser().getId())) {
            throw new AccessDeniedException("");
        }

        if (application.getStatus() == Application.Status.IN_PROGRESS) {
            application.cancel();
        }
        return application;
    }


}
