# JobRunr example

This repository shows an example how you can integrate JobRunr with [spring.io](https://spring.io/).

## About this project
This project exists out of 3 modules:
- **core**: this project contains only two service:
  1. [MyService](core/src/main/java/org/jobrunr/examples/services/MyService.java), a simple spring service with two example methods which you want to run in a background server.
  2. [RecurringJobService](core/src/main/java/org/jobrunr/examples/services/RecurringJobService.java), a simple spring service with a method annotated with `@Recurring`, which with then be picked up by a JobScheduler during Spring Boot application boostraping.
- **processingapp**: this app is a Spring Console application and runs indefinitely. It polls for new background jobs and processes them when they arrive. It contains only a single class called [JobServerApplication](processingapp/src/main/java/org/jobrunr/examples/processingapp/JobServerApplication.java) which defines the JobRunr beans to process jobs.
- **webapp**: this is a Spring Rest Webapp that enqueues new background jobs. It contains a simple `RestController` called [JobController](webapp/src/main/java/org/jobrunr/examples/webapp/api/JobController.java) which contains two GET methods to enqueue jobs.

## How to run this project:
- clone the project and open it in your favorite IDE that supports gradle
- Run the main method from the [WebApplication](webapp/src/main/java/org/jobrunr/examples/webapp/WebApplication.java) in the `webapp` module and keep it running
- Run the main method from the [JobServerApplication](processingapp/src/main/java/org/jobrunr/examples/processingapp/JobServerApplication.java) in the `processingapp` module and also keep it running
- Open your favorite browser:
  - Navigate to the JobRunr dashboard located at http://localhost:8000/dashboard. This is running within the [JobServerApplication](processingapp/src/main/java/org/jobrunr/examples/processingapp/JobServerApplication.java) as it defines a bean of type [JobRunrDashboardWebServer](https://github.com/jobrunr/jobrunr/blob/master/core/src/main/java/org/jobrunr/dashboard/JobRunrDashboardWebServer.java)
  - To enqueue a simple job, open a new tab and go to http://localhost:8080/simple-job?name=your-name
  - To enqueue a long-running job, open a new tab and go to http://localhost:8080/long-running-job?name=your-name