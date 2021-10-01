package com.management.food.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.food.entity.Lecture;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LectureDTO {
    Long id;

    String name;

    String place;

    String date;

}
