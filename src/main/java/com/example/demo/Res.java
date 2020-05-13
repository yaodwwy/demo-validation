package com.example.demo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author adam
 * REST results
 */
@Data
@Accessors(chain = true)
public class Res<R> implements Serializable {
    private int code = 200;
    private String message = "成功";
    private R data;
}