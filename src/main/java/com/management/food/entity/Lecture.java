package com.management.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    Integer time;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Teacher teacher;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Food food;


    public void update(String name, String place, LocalDate dateAt, LocalTime fromAt, Integer time, Teacher teacher, Food food) {
        this.name = name;
        this.place = place;
        this.dateAt = dateAt;
        this.fromAt = fromAt;
        this.time = time;
        this.teacher = teacher;
        this.food = food;
    }

    public Lecture setFood(Food food) {
        this.food = food;
        return this;
    }

    public Lecture setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public String getTeacherName() {
        return teacher.getName();
    }

    public Long getFoodId() {
        return food.getId();
    }

    public int getCost() {
        return food.getCost();
    }


}
