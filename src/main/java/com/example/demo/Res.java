package com.example.demo;

import lombok.Builder;
import lombok.Data;

/**
 * @author adam
 * REST results
 */
@Data
public class Res<R> {
    private int code = 200;
    private String message = "成功";
    private R data;
}