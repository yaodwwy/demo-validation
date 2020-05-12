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
        for (ConstraintViolation<Foo> cv : validate) {

            message.append("{" + cv.getMessage() + "}");
        }
        result.setData(foo);
        return result;
    }
}