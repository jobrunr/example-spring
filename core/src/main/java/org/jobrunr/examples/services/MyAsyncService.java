package org.jobrunr.examples.services;

import org.jobrunr.jobs.annotations.AsyncJob;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Component;

@Component
@AsyncJob
public class MyAsyncService {

    @Job(name = "my async spring job")
    public void simpleAsyncJob() {
        System.out.println("Doing some work that's auto-async without needing a jobScheduler");
    }
}
