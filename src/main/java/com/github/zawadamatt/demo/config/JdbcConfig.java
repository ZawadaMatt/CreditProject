package com.github.zawadamatt.demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/nazwabazydanych?serverTimezone=UTC"); //adres servera bazy danych, ustawienie strefy czasowej
        dataSourceBuilder.username("nazwa uzytkownika"); //nazwa użytkownika
        dataSourceBuilder.password("haslo"); //hasło do bazy danych,
        // dataSourceBuilder.driverClassName() (opcjonalnie dodać sterownik bazy danych
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

}
