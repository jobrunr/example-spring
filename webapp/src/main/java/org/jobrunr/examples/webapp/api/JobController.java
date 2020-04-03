package org.jobrunr.examples.webapp.api;

import org.jobrunr.examples.services.MyService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobScheduler jobScheduler;

    public JobController(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    @GetMapping("/simple-job")
    public JobResponse simpleJob(@RequestParam(value = "name", defaultValue = "World") String name) {

        jobScheduler.<MyService>enqueue(myService -> myService.doSimpleJob("Hello " + name));

        return new JobResponse("Job Enqueued");
    }

    @GetMapping("/long-running-job")
    public JobResponse longRunningJob(@RequestParam(value = "name", defaultValue = "World") String name) {

        jobScheduler.<MyService>enqueue(myService -> myService.doLongRunningJob("Hello " + name));

        return new JobResponse("Job Enqueued");
    }
}
