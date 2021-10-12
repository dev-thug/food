package com.management.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernatieLazyInitializer", "handler"})
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(length = 500)
    String ingredients;

    String image;

    Integer cost;

    String part;

    public Food(String name, String ingredients, String image, Integer cost, String part) {
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
        this.cost = cost;
        this.part = part;
    }

    public Food updateCost(Integer cost) {
        this.cost = cost;
        return this;
    }
}
