package org.jobrunr.examples.webapp.api;

import org.jobrunr.examples.services.MyService;
import org.jobrunr.examples.services.MyServiceInterface;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobScheduler jobScheduler;
    private final MyServiceInterface myService;

    public JobController(JobScheduler jobScheduler, MyServiceInterface myService) {
        this.jobScheduler = jobScheduler;
        this.myService = myService;
    }

    @GetMapping("/simple-job")
    public JobResponse simpleJob(@RequestParam(value = "name", defaultValue = "World") String name) {
        jobScheduler.<MyService>enqueue(myService -> myService.doSimpleJob("Hello " + name));

        return new JobResponse("Job Enqueued");
    }

    @GetMapping("/simple-job-instance")
    public JobResponse simpleJobUsingInstance(@RequestParam(value = "name", defaultValue = "World") String name) {
        jobScheduler.enqueue(() -> myService.doSimpleJob("Hello " + name));

        return new JobResponse("Job Enqueued");
    }

    @GetMapping("/long-running-job")
    public JobResponse longRunningJob(@RequestParam(value = "name", defaultValue = "World") String name) {
        jobScheduler.<MyService>enqueue(myService -> myService.doLongRunningJob("Hello " + name));

        return new JobResponse("Job Enqueued");
    }

    @GetMapping("/long-running-job-with-job-context")
    public JobResponse longRunningJobWithJobContext(@RequestParam(value = "name", defaultValue = "World") String name) {
        jobScheduler.<MyService>enqueue(myService -> myService.doLongRunningJobWithJobContext("Hello " + name, JobContext.Null));

        return new JobResponse("Job Enqueued");
    }
}
