package org.jobrunr.examples;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.sqlite.SqLiteStorageProvider;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.nio.file.Paths;

@Configuration
@ComponentScan(basePackageClasses = JobRunrStorageConfiguration.class)
public class JobRunrStorageConfiguration {

    @Bean
    public StorageProvider storageProvider(DataSource dataSource, JobMapper jobMapper) {
        final SqLiteStorageProvider sqLiteStorageProvider = new SqLiteStorageProvider(dataSource);
        sqLiteStorageProvider.setJobMapper(jobMapper);
        return sqLiteStorageProvider;
    }

    @Bean
    public SQLiteDataSource dataSource() {
        final SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:" + Paths.get(System.getProperty("java.io.tmpdir"), "jobrunr.db"));
        return dataSource;
    }

    @Bean
    public JobMapper jobMapper(JacksonJsonMapper jsonMapper) {
        return new JobMapper(jsonMapper);
    }

    @Bean
    public JacksonJsonMapper jsonMapper() {
        return new JacksonJsonMapper();
    }

}
