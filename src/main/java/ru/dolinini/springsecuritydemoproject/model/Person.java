package ru.dolinini.springsecuritydemoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String sureName;

}
