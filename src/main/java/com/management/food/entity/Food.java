package com.management.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernatieLazyInitializer", "handler"})
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String ingredients;

    String image;

    Integer cost;

    public Food(String name, String ingredients, String image, Integer cost) {
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
        this.cost = cost;
    }

    public Food updateCost(Integer cost) {
        this.cost = cost;
        return this;
    }
}
