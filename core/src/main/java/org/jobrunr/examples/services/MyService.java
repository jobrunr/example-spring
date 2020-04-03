package org.jobrunr.examples.services;

import org.springframework.stereotype.Component;

/**
 * This is a simple spring service
 */
@Component
public class MyService {

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
}
