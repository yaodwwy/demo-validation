package com.example.demo.customize;

import com.example.demo.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author adam
 * 捕获Spring boot抛出的BindException异常
 */
@Slf4j
@RestControllerAdvice
public class BindExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Res<String> handleBindException(BindException ex) {
        Res<String> errorResult = new Res<>();
        List<FieldError> allErrors = ex.getFieldErrors();
        allErrors.forEach(fe -> {
            errorResult.setMessage(fe.getDefaultMessage());
            // 例：字段 name 的参数 null 不合法 name不能为空
            log.warn("字段 {} 的参数 {} 不合法 {}", fe.getField(), fe.getRejectedValue(), fe.getDefaultMessage());

        });
        // 生成返回结果
        errorResult.setCode(400);
        return errorResult;
    }

}
