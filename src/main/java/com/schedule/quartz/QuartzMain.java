package com.schedule.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by MyWorld on 2016/9/12.
 */
@Service
public class QuartzMain implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzMain.class);
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @Override
    public void run(String... args) throws Exception {
        start();
    }

    public void start() throws SchedulerException {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
        try {
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("myJob", "myGroup")
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "myGroup")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            LOGGER.error("Fail to start scheduler", e);
            JobExecutionException jobExecutionException = new JobExecutionException(e);
            // Quartz will automatically unschedule
            // all triggers associated with this job
            // so that it does not run again
            jobExecutionException.setUnscheduleAllTriggers(true);
            throw jobExecutionException;
        }
    }
}
