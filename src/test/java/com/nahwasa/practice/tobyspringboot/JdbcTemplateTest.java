package com.nahwasa.practice.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*;

@TobySpringBootTest
//@Rollback(value = false)  이렇게 두면 @Transactional이 롤백 안시키므로 테스트 두개 중 하나는 실패하게 됨.
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("insert into hello values(?, ?)", "nahwasa", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "Toby", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void insertAndQuery2() {    // Transactional 준게 제대로 롤백 시키는지 확인
        jdbcTemplate.update("insert into hello values(?, ?)", "nahwasa", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "Toby", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        assertThat(count).isEqualTo(2);
    }
}
