package com.management.food.entity;

import com.management.food.dto.LectureDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String place;

    LocalDate dateAt;

    LocalTime fromAt;

    LocalTime toAt;

    @ManyToOne(fetch = FetchType.LAZY)
    Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    Food food;


    public static Lecture of(LectureDTO lectureDTO) {

        return Lecture.builder()
                .id(lectureDTO.getId()).name(lectureDTO.getName())
                .place(lectureDTO.getPlace()).dateAt(lectureDTO.getDateAt())
                .fromAt(lectureDTO.getFromAt()).toAt(lectureDTO.getToAt())
                .build();
    }

    public Lecture setFood(Food food) {
        this.food = food;
        return this;
    }

    public Lecture setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }


}
