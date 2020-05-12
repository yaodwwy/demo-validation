package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Bar {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "name不能为空")
    @Size(min = 2, max = 30)
    private String name;
}
