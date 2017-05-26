package com.tangcheng.app.schedule.service;

import com.tangcheng.app.schedule.domain.vo.JobVO;
import com.tangcheng.app.schedule.job.EchoJob;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tangcheng on 4/29/2017.
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public List<JobVO> listAll() throws SchedulerException {
        List<String> jobGroupNames = scheduler.getJobGroupNames();
        List<JobVO> voList = new ArrayList<>(jobGroupNames.size());
        for (String jobGroupName : jobGroupNames) {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.groupEquals(jobGroupName));
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    /**
                     state的值代表该任务触发器的状态：
                     STATE_BLOCKED   4 // 运行
                     STATE_COMPLETE  2  //完成那一刻，不过一般不用这个判断Job状态
                     STATE_ERROR     3  // 错误
                     STATE_NONE  -1      //未知
                     STATE_NORMAL    0   //正常无任务，用这个判断Job是否在运行
                     STATE_PAUSED    1   //暂停状态
                     */
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    JobVO vo = new JobVO();
                    vo.setName(jobKey.getName());
                    vo.setGroup(jobKey.getGroup());
                    vo.setDescription(jobDetail.getDescription());
                    vo.setStatus(triggerState.name());
                    vo.setDescription(jobDetail.getDescription());
                    vo.setStartTime(DateFormatUtils.format(trigger.getStartTime(), "yyyy-MM-dd HH:mm:ss sss"));
                    if (trigger instanceof CronTrigger) {
                        vo.setCronExpression(((CronTrigger) trigger).getCronExpression());
                    } else if (trigger instanceof SimpleTrigger) {
                        long repeatInterval = ((SimpleTrigger) trigger).getRepeatInterval();
                        vo.setCronExpression("Repeat Interval:" + repeatInterval);
                    }

                    voList.add(vo);
                }
            }

        }

        return voList;
    }

    @Override
    public void save(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        if (scheduler.checkExists(triggerKey)) {
            LOGGER.warn("{},{} already exists.nothing to do!", name, group);
            return;
        }
        JobKey jobKey = JobKey.jobKey(name, group);
        String currentDateTime = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(new Date());
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever(20);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withSchedule(scheduleBuilder)
                .withDescription(currentDateTime)
                .build();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(EchoJob.RANDOM_VALUE, ThreadLocalRandom.current().nextInt());
        JobDetail jobDetail = JobBuilder.newJob(EchoJob.class).withIdentity(jobKey)
                .setJobData(jobDataMap)
                .usingJobData(EchoJob.CREATE_TIME, currentDateTime)
                .withDescription("job group")
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void remove(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        if (scheduler.checkExists(triggerKey)) {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
//            scheduler.deleteJob(JobKey.jobKey(name, group));
            return;
        }
        LOGGER.warn("{},{} not exists.nothing to do!", name, group);
    }

    @Override
    public void resume(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        if (!scheduler.checkExists(triggerKey)) {
            LOGGER.warn("{},{} not exists.nothing to do!", name, group);
            return;
        }
        if (!scheduler.getTriggerState(triggerKey).equals(Trigger.TriggerState.PAUSED)) {
            LOGGER.warn("{},{} not pause.nothing to do!", name, group);
            return;
        }
        scheduler.resumeTrigger(triggerKey);
    }

    @Override
    public void pause(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        if (!scheduler.checkExists(triggerKey)) {
            LOGGER.warn("{},{} not exists.nothing to do!", name, group);
            return;
        }
        if (!scheduler.getTriggerState(triggerKey).equals(Trigger.TriggerState.NORMAL)) {
            LOGGER.warn("{},{} not running.nothing to do!", name, group);
            return;
        }
        scheduler.pauseTrigger(triggerKey);
    }


}
