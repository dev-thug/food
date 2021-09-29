package com.management.food.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    Student student;

    int credit;
    int refund;

    LocalDate creditAt;
    LocalDate refundAt;
    LocalDate cancelAt;
}
