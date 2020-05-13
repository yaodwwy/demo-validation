package com.example.demo;

import com.example.demo.model.Foo;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ServiceImpl {

    private final Validator validator;

    public ServiceImpl(Validator validator) {
        this.validator = validator;
    }

    public Res<Foo> validate(Foo foo) {
        Res<Foo> result = new Res<>();
        StringBuilder message = new StringBuilder();

        Set<ConstraintViolation<Foo>> validate = validator.validate(foo);
        if (!validate.isEmpty()) {
            result.setCode(4000);
        }
        for (ConstraintViolation<Foo> cv : validate) {
            message.append("{ 字段名：" + cv.getPropertyPath() + " 的值：" + cv.getInvalidValue() + ": " + cv.getMessage() + "},");
        }
        result.setMessage(message.toString()).setData(foo);
        return result;
    }
}