package ru.kuzmina.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDto implements Serializable {
    private final Long id;
    private final String name;
    private final Double mark;
    private final Integer age;
}
