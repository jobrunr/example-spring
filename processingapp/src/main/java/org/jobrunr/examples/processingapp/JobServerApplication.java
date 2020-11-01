package org.jobrunr.examples.processingapp;

import org.jobrunr.examples.JobRunrStorageConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JobRunrStorageConfiguration.class)
public class JobServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobServerApplication.class, args);
    }
}
