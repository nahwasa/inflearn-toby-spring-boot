package com.nahwasa.practice.tobyspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("Toby")).isNull();
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
