package com.example.demo;

import com.example.demo.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/test-a")
    public Res<Foo> checkPojo(@RequestBody @Valid Foo foo, BindingResult results) {
        String defaultMessage = null;
        if (results.hasErrors()) {
            defaultMessage = Objects.requireNonNull(results.getFieldError()).getDefaultMessage();
        }
        Res<Foo> queryResult = new Res<>();
        queryResult.setCode(200);
        queryResult.setMessage(defaultMessage);
        queryResult.setData(foo);
        return queryResult;
    }

    @PostMapping("/test-b")
    public Res<Foo> checkQuery(@RequestBody @Validated Foo user, BindingResult result) {
        StringBuilder sBuilder = new StringBuilder();
        Res<Foo> queryResult = new Res<>();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                log.info(error.getCode() + "---" + "---" + error.getDefaultMessage());
                sBuilder.append(error.getDefaultMessage());
                sBuilder.append("\n");
            }
            queryResult.setCode(400);
        }
        queryResult.setMessage(sBuilder.toString());
        queryResult.setData(user);
        return queryResult;
    }

    /**
     * 服务层验证测试
     *
     * @param user
     * @return
     */
    @PostMapping("/test-c")
    public Res<Foo> checkService(Foo user) {
        return service.validate(user);
    }

    /**
     * 控制层验证全局异常捕获
     *
     * @param model
     * @param result
     * @return
     */
    @PostMapping("/test-d")
    public Res<Foo> checkHandler(@RequestBody @Valid Foo model, BindingResult result) throws BindException {
        Res<Foo> queryResult = new Res<>();
        queryResult.setData(model);
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        return queryResult;
    }

}
