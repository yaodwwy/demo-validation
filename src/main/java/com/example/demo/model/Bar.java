package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Bar {

    @NotNull
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
}
