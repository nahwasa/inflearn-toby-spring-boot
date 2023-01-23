package com.nahwasa.practice.tobyspringboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator helloService = new HelloDecorator(name -> name);

        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("*Test*");
    }
}