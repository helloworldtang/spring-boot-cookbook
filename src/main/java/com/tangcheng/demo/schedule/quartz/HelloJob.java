package com.tangcheng.demo.schedule.quartz;

import com.tangcheng.demo.schedule.quartz.service.IBusinessService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by MyWorld on 2016/9/12.
 */
@Component
public class HelloJob implements Job, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);
    @Autowired
    private IBusinessService businessService;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        businessService.doBusiness();
        LOGGER.info("{}, Hello!  HelloJob is executing.", jobExecutionContext.getJobDetail().getKey());
    }

}
