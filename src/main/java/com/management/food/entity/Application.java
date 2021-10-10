package com.management.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Lecture lecture;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @Setter
    int cost;

    @Setter
    int refund;

    LocalDateTime applyAt;


    LocalDateTime completeAt;

    LocalDateTime refundAt;

    LocalDateTime cancelAt;
    @Setter
    Status status;

    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        CANCELED,
        REFUNDED
    }

    public Application(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
        this.status = Status.IN_PROGRESS;
        this.cost = 0;
        this.applyAt = LocalDateTime.now();
    }

    public Long getLectureId() {
        return lecture.getId();
    }

    public Long getStudentId() {
        return user.getId();
    }

    public void complete(int cost) {
        this.cost = cost;
        this.status = Status.COMPLETED;
        this.completeAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = Status.CANCELED;
        this.cancelAt = LocalDateTime.now();
    }

    public void refund() {
        this.status = Status.REFUNDED;
        this.cost = 0;
        this.refundAt = LocalDateTime.now();
    }


}
