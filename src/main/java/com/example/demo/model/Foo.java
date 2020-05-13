package com.example.demo.model;

import com.example.demo.customize.CellPhone;
import com.example.demo.customize.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class Foo implements Serializable {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "{foo.name}")
    @Size(min = 2, max = 30)
    private String name;

    @Range(max = 150, min = 1, message = "{foo.size}")
    private Integer size;

    @NotEmpty(message = "{foo.password}")
    @Length(min = 6, max = 8, message = "{foo.password.length}")
    @Pattern(regexp = "[a-zA-Z]*", message = "{foo.password.regex}")
    private String password;

    @Email
    private String email;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    /**
     * 子列表验证
     */
    @Valid
    @NotNull(message = "bar列表不能为空")
    private List<Bar> bars;

    /**
     * 自定义规则注解
     */
    @CellPhone
    private String cellphone;
    @Gender
    private String gender;
}
