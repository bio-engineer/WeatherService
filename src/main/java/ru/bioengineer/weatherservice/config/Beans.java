package ru.bioengineer.weatherservice.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class Beans {

    private final Environment env;

    @Autowired
    public Beans(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(env.getProperty("SPRING_DATASOURCE_URL"));
        hikariConfig.setUsername(env.getProperty("SPRING_DATASOURCE_USERNAME"));
        hikariConfig.setPassword(env.getProperty("SPRING_DATASOURCE_PASSWORD"));
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setPoolName("hikari");
        return new HikariDataSource(hikariConfig);
    }
}
