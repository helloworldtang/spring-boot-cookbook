package com.tangcheng.app.schedule.service;

import com.tangcheng.app.schedule.domain.vo.JobVO;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by tangcheng on 4/29/2017.
 */
public interface QuartzService {
    List<JobVO> listAll() throws SchedulerException;

    void save(String name, String desc) throws SchedulerException;

    void remove(String name, String desc) throws SchedulerException;

    void resume(String name, String desc) throws SchedulerException;

    void pause(String name, String group) throws SchedulerException;
}
