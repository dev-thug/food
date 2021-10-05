package com.management.food.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    @NotEmpty
    @Size(min = 2, max = 50)
    String name;
    String account;

}
