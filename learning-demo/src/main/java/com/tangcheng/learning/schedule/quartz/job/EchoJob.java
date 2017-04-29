package com.tangcheng.learning.schedule.quartz.job;

import com.tangcheng.learning.schedule.quartz.biz.IEchoBiz;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by MyWorld on 2016/9/12.
 */
@Component
public class EchoJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoJob.class);
    @Autowired
    private IEchoBiz echoBiz;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        echoBiz.doBusiness();
        LOGGER.info("{}, Hello!  HelloJob is executing.", jobExecutionContext.getJobDetail().getKey());
    }

}
