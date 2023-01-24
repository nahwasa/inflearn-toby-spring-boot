package com.nahwasa.practice.config.autoconfig;

import com.nahwasa.practice.config.ConditionalMyOnClass;
import com.nahwasa.practice.config.EnableMyConfigurationProperties;
import com.nahwasa.practice.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations") // 그래들에 'org.springframework.boot:spring-jdbc' 넣어줘야 뜸.
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
public class DataSourceConfig {
    @Bean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();   // pool 이 없고 매번 새로운 커넥션을 만드는 정말 심플한 데이터소스임

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

}
