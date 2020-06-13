package org.jobrunr.examples.services;

import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is a simple spring service
 */
@Component
public class MyService implements MyServiceInterface {

    private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(MyService.class));

    public void doSimpleJob(String anArgument) {
        System.out.println("Doing some work: " + anArgument);
    }

    public void doLongRunningJob(String anArgument) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void doLongRunningJobWithJobContext(String anArgument, JobContext jobContext) {
        try {
            LOGGER.warn("Starting long running job...");
            final JobDashboardProgressBar progressBar = jobContext.progressBar(10);
            for (int i = 0; i < 10; i++) {
                LOGGER.info(String.format("Processing item %d: %s", i, anArgument));
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(15000);
                progressBar.increaseByOne();
            }
            LOGGER.warn("Finished long running job...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
