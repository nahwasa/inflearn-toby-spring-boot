package com.nahwasa.practice.config.autoconfig;

import com.nahwasa.practice.config.ConditionalMyOnClass;
import com.nahwasa.practice.config.EnableMyConfigurationProperties;
import com.nahwasa.practice.config.MyAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations") // 그래들에 'org.springframework.boot:spring-jdbc' 넣어줘야 뜸.
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean   // 혹시 사용자 영역에서 히카리를 설정할수도있으므로 설정해둔거.
    DataSource hikariDataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        HikariDataSource dataSource = new HikariDataSource();   // pool 이 없고 매번 새로운 커넥션을 만드는 정말 심플한 데이터소스임

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();   // pool 이 없고 매번 새로운 커넥션을 만드는 정말 심플한 데이터소스임

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class) // 스프링 컨테이너에 DataSource가 딱 한개만 등록되어 있다면 그걸 사용하겠다는 의미
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
