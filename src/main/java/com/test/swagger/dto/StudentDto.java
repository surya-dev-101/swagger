package com.test.swagger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private int age;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
