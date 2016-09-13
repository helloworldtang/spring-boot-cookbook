package com.schedule.quartz.factory;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.scheduling.quartz.DelegatingJob;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;


public class AdaptableJobFactory implements JobFactory {
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        return newJob(bundle);
    }

    public Job newJob(TriggerFiredBundle bundle) throws SchedulerException {
        try {
            Object jobObject = createJobInstance(bundle);
            return adaptJob(jobObject);
        } catch (Exception ex) {
            throw new SchedulerException("Job instantiation failed", ex);
        }
    }

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // Reflectively adapting to differences between Quartz 1.x and Quartz 2.0...
        Method getJobDetail = bundle.getClass().getMethod("getJobDetail");
        Object jobDetail = ReflectionUtils.invokeMethod(getJobDetail, bundle);
        Method getJobClass = jobDetail.getClass().getMethod("getJobClass");
        Class jobClass = (Class) ReflectionUtils.invokeMethod(getJobClass, jobDetail);
        return jobClass.newInstance();
    }

    protected Job adaptJob(Object jobObject) throws Exception {
        if (jobObject instanceof Job) {
            return (Job) jobObject;
        } else if (jobObject instanceof Runnable) {
            return new DelegatingJob((Runnable) jobObject);
        } else {
            throw new IllegalArgumentException("Unable to execute job class [" + jobObject.getClass().getName() +
                    "]: only [org.quartz.Job] and [java.lang.Runnable] supported.");
        }
    }

}