package com.nahwasa.practice.tobyspringboot;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;   // 인터페이스지만 스프링이 알아서 컨테이너 뒤져서 알아서 구현체 찾아서 넣어줌.
    }

    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
