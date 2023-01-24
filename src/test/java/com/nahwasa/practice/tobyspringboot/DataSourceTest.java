package com.nahwasa.practice.tobyspringboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)  // 스프링 컨텍스트를 이용한 스프링 컨테이너 테스트가 가능해짐
@ContextConfiguration(classes = TobySpringBootApplication.class)    // 모든 빈 구성정보를 끌어오는 시작점이 되는 클래스 넣어줌. 여기서 ComponentScan으로 설정해둔것들 다 불러옴.
@TestPropertySource("classpath:/application.properties")    // 프로퍼티도 불러올 수 있도록 해줌.
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
