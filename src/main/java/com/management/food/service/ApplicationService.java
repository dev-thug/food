package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Application;
import com.management.food.entity.Lecture;
import com.management.food.repository.ApplicationRepository;
import com.management.food.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final LectureRepository lectureRepository;
    private final UserService userService;

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
    public Application complete(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        if (application.getStatus() == Application.Status.IN_PROGRESS) {
            application.complete();
        }
        return application;
    }

    @Transactional
    public Application refund(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        if (application.getStatus() == Application.Status.COMPLETED) {
            application.refund();
        }
        return application;
    }

    @Transactional
    public Application cancel(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(NotFoundResourceException::new);
        if (application.getStatus() == Application.Status.IN_PROGRESS) {
            application.cancel();
        }
        return application;
    }


}
