package com.management.food.advice.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private Integer code;
    private String message;
}
