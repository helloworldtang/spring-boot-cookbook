package com.tangcheng.app.schedule.job;

import com.tangcheng.app.schedule.biz.EchoBiz;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tangcheng on 4/30/2017.
 */
public class EchoJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoJob.class);
    public static final String RANDOM_VALUE = "randomValue";
    public static final String CREATE_TIME = "createTime";

    @Autowired
    private EchoBiz echoBiz;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("================job start================");
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        echoBiz.echo(jobDataMap.getIntValue(RANDOM_VALUE));
        echoBiz.echo(jobDataMap.getString(CREATE_TIME));
        LOGGER.info("================job end================");
    }
}
