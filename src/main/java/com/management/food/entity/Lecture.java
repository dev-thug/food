package com.management.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Food food;


    public void update(String name, String place, LocalDate dateAt, LocalTime fromAt, Integer time) {
        this.name = name;
        this.place = place;
        this.dateAt = dateAt;
        this.fromAt = fromAt;
        this.time = time;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTeacherName() {
        return user.getName();
    }

    public Long getFoodId() {
        return food.getId();
    }

    public int getCost() {
        return food.getCost();
    }


}
