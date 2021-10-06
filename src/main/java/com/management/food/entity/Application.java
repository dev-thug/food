package com.management.food.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    LocalDateTime creditAt;
    LocalDateTime refundAt;
    LocalDateTime cancelAt;

    Status status;

    enum Status{
        IN_PROGRESS,
        COMPLETED,
        CANCELLED,
        REFUNDED
    }
}
