package org.jobrunr.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import java.nio.file.Paths;

@Configuration
@ComponentScan(basePackageClasses = JobRunrStorageConfiguration.class)
public class JobRunrStorageConfiguration {

    @Bean
    public SQLiteDataSource dataSource() {
        final SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:" + Paths.get(System.getProperty("java.io.tmpdir"), "jobrunr-example.db"));
        return dataSource;
    }

}
