package com.management.food.repository;

import com.management.food.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
