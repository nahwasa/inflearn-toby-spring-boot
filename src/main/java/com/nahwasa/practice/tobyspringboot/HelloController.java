package com.nahwasa.practice.tobyspringboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;   // 인터페이스지만 스프링이 알아서 컨테이너 뒤져서 알아서 구현체 찾아서 넣어줌.
        this.applicationContext = applicationContext;
        System.out.println(applicationContext);
    }

    @GetMapping("/hello")
    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
