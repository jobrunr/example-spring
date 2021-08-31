package org.jobrunr.examples.services;

import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Component;

@Component
public class RecurringJobService {

    @Recurring(id = "my-recurring-job", cron = "*/15 * * * *")
    public void myRecurringMethod() {
        System.out.print("My recurring job method");
    }

}
