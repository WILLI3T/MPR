package com.example.MPR.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Car {

    private Long id;
    @NotBlank(message = "Nazwa jest wymagana")
    private String name;

    @NotNull(message = "Wiek jest wymagany")
    private Integer age;

    public Car(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    protected Car() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

