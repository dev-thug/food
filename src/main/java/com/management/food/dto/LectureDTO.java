package com.management.food.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {

    String name;

    String place;


    //    @Parameter(name = "강의 날짜", description = "강의를 하는 날짜 입니다.", example = "2021-10-05")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", message = "날짜 포멧")
    String date;
    //HH:mm
//    @Parameter(name = "강의 시작 시간", description = "강의를 시작하는 시간 입니다.", example = "09:00")
    @DateTimeFormat(pattern = "HH:mm")
    @Pattern(regexp = "(0[0-9]|1[0-9]|2[0-4]):(0[0-9]|[1-5][0-9])", message = "시간 포멧")
    String fromAt;

    @Pattern(regexp = "([1-9]|0[1-9]|1[0-9]|2[0-4])", message = "강의 시간")
    int time;

    long foodId;



}