package org.jobrunr.examples.processingapp;

import org.jobrunr.dashboard.JobRunrDashboardWebServer;
import org.jobrunr.examples.JobRunrStorageConfiguration;
import org.jobrunr.server.BackgroundJobServer;
import org.jobrunr.server.JobActivator;
import org.jobrunr.server.jmx.JobRunrJMXExtensions;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.utils.mapper.JsonMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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
    public JobRunrDashboardWebServer dashboardWebServer(StorageProvider storageProvider, JsonMapper jsonMapper) {
        final JobRunrDashboardWebServer jobRunrDashboardWebServer = new JobRunrDashboardWebServer(storageProvider, jsonMapper);
        jobRunrDashboardWebServer.start();
        return jobRunrDashboardWebServer;
    }

    @Bean
    public BackgroundJobServer backgroundJobServer(StorageProvider storageProvider, JobActivator jobActivator) {
        final BackgroundJobServer backgroundJobServer = new BackgroundJobServer(storageProvider, jobActivator);
        backgroundJobServer.start();
        return backgroundJobServer;
    }

    @Bean
    public JobRunrJMXExtensions jobRunrJMXExtensions(BackgroundJobServer backgroundJobServer, StorageProvider storageProvider) {
        return new JobRunrJMXExtensions(backgroundJobServer, storageProvider);
    }

    @Bean
    public JobActivator jobActivator(ApplicationContext applicationContext) {
        return applicationContext::getBean;
    }
}
