package org.jobrunr.examples.webapp;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "org.jobrunr.examples.services")
public class WebConfiguration {

    @Bean
    public JdbcDataSource dataSource() {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9090/mem:jobrunr-db;DB_CLOSE_DELAY=-1");
        return dataSource;
    }
}
