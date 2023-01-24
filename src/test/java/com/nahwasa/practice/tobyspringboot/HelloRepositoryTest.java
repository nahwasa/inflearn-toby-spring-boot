package com.nahwasa.practice.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@TobySpringBootTest
public class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("nahwasa")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("nahwasa")).isEqualTo(0);

        helloRepository.increaseCount("nahwasa");
        assertThat(helloRepository.countOf("nahwasa")).isEqualTo(1);

        helloRepository.increaseCount("nahwasa");
        assertThat(helloRepository.countOf("nahwasa")).isEqualTo(2);
    }
}
