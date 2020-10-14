package org.jobrunr.examples.processingapp;

import org.jobrunr.examples.JobRunrStorageConfiguration;
import org.jobrunr.server.BackgroundJobServer;
import org.jobrunr.server.jmx.JobRunrJMXExtensions;
import org.jobrunr.storage.StorageProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JobRunrStorageConfiguration.class)
public class JobServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JobServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        Thread.currentThread().join();
    }

    @Bean
    public JobRunrJMXExtensions jobRunrJMXExtensions(BackgroundJobServer backgroundJobServer, StorageProvider storageProvider) {
        return new JobRunrJMXExtensions(backgroundJobServer, storageProvider);
    }

}
