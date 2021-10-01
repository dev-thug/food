package com.management.food.entity;

import com.management.food.dto.LectureDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateAt;

    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime fromAt;

    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime toAt;

    @ManyToOne(fetch = FetchType.LAZY)
    Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    Food food;


    public Lecture setFood(Food food) {
        this.food = food;
        return this;
    }

    public Lecture setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }


}
