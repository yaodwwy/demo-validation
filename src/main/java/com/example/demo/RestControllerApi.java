package com.example.demo;

import com.example.demo.model.Bar;
import com.example.demo.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("valid")
public class RestControllerApi {

    private final ServiceImpl service;

    public RestControllerApi(ServiceImpl service) {
        this.service = service;
    }

    /**
     * 第一个验证用例
     */
    @PostMapping("bar")
    public String bar(@RequestBody @Valid Bar bar, BindingResult results) {
        if (results.hasErrors()) {
            FieldError error = results.getFieldError();
            return error.getField() + " " + error.getDefaultMessage();
        }
        return "hi! " + bar.getName();
    }


    /**
     * http状态码会是200
     */
    @PostMapping("http-status-200")
    public Res<Foo> returnWithHttpStatus200(@RequestBody @Valid Foo foo, BindingResult results) {
        String defaultMessage = "通过";
        String field = "";
        if (results.hasErrors()) {
            field = " at field: " + Objects.requireNonNull(results.getFieldError()).getField();
            defaultMessage = Objects.requireNonNull(results.getFieldError()).getDefaultMessage();
        }
        return new Res<Foo>().setMessage("验证结果 " + defaultMessage + field)
                .setData(foo);
    }

    /**
     * http状态码会是400
     */
    @PostMapping("http-status-400")
    public Res<Foo> returnWithHttpStatus400(@RequestBody @Validated Foo foo, BindingResult result) {
        StringBuilder defaultMessage = new StringBuilder();
        Res<Foo> queryResult = new Res<>();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            list.forEach(error -> {
                defaultMessage.append(error.getCode() + error.getDefaultMessage());
            });
            queryResult.setCode(4000);
        }
        return queryResult.setMessage(defaultMessage.toString()).setData(foo);
    }

    /**
     * 服务层验证测试
     */
    @PostMapping("service-level")
    public Res<Foo> validateServiceImpl(Foo foo) {
        return service.validate(foo);
    }

    /**
     * 控制层验证全局异常捕获
     */
    @PostMapping("direct-throw-exception")
    public Res<Foo> controllerDirectThrowException(@RequestBody @Valid Foo model, BindingResult result) throws BindException {
        Res<Foo> queryResult = new Res<>();
        queryResult.setData(model);
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        return queryResult;
    }

}
