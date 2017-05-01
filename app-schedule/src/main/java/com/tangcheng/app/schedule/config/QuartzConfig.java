package com.tangcheng.app.schedule.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by tangcheng on 4/29/2017.
 */
@Configuration
public class QuartzConfig {

    /**
     * quartz job即没有报错，也不执行。可以会以下两个方面进行排查：
     * （1）OutOfMemory
     * （2）增大threadCount
     * （3）所有的job，listener都要try catch(Exception e)
     *
     * @param springJobFactory
     * @return
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler(SpringJobFactory springJobFactory) throws SchedulerException {
        /**
         * 如果不使用下面的的Properties配置，
         * 默认会使用quartz.2.3.2.jar中的org.quartz.quartz.properties文件
         */
        SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.setJobFactory(springJobFactory);
        scheduler.start();
        return scheduler;
    }

    /**
     * 2016-06-01 12:24:44 [main:9169] - [INFO] Using default implementation for ThreadExecutor
     * 2016-06-01 12:24:44 [main:9206] - [INFO] Initialized Scheduler Signaller of type: class org.quartz.core.SchedulerSignalerImpl
     * 2016-06-01 12:24:44 [main:9206] - [INFO] Quartz Scheduler v.2.2.1 created.
     * 2016-06-01 12:24:44 [main:9208] - [INFO] RAMJobStore initialized.
     * 2016-06-01 12:24:44 [main:9208] - [INFO] Scheduler meta-data: Quartz Scheduler (v2.2.1) 'Timer' with instanceId 'NON_CLUSTERED'
     * Scheduler class: 'org.quartz.core.QuartzScheduler' - running locally.
     * NOT STARTED.
     * Currently in standby mode.
     * Number of jobs executed: 0
     * Using thread pool 'org.quartz.simpl.SimpleThreadPool' - with 20 threads.
     * Using job-store 'org.quartz.simpl.RAMJobStore' - which does not support persistence. and is not clustered.
     * <p>
     * 2016-06-01 12:24:44 [main:9209] - [INFO] Quartz scheduler 'Timer' initialized from an externally provided properties instance.
     * 2016-06-01 12:24:44 [main:9209] - [INFO] Quartz scheduler version: 2.2.1
     *
     * @return
     */
    public Properties quartzProperties() {
        Properties properties = new Properties();
        properties.put("org.quartz.scheduler.instanceName", "Timer");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");
        properties.put("org.quartz.scheduler.skipUpdateCheck", "true");
        properties.put("org.quartz.scheduler.rmi.export", false);
        properties.put("org.quartz.scheduler.rmi.proxy", false);
        properties.put("org.quartz.scheduler.wrapJobExecutionInUserTransaction", false);
        properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount", "5");
        properties.put("org.quartz.threadPool.threadPriority", "5");
        properties.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", true);
/*        quartz有个全局的参数misfireThreshold设置可以允许的超时时间，超过了就不执行，未超过就执行。
        比如设置了misfireThreshold=30分钟，如果一个任务定时在10:30执行，但在10:29服务器挂了，在10:50启动，虽然任务超时了21分钟，但小于misfireThreshold，所以还是可以执行。而如果服务器11:10才启动，那就misfire了。

        对于周期性的任务，如果有misfire的情况出现，则会自动更新CronTrigger的时间周期
        默认情况下会在当前时间马上执行前一个被misfire的任务
        而如果设置MISFIRE_INSTRUCTION_DO_NOTHING，则不对misfire的任务做特殊处理，只从当前时间之后的下一次正常调度时间开始执行*/
        long misfireThreshold = 5 * 60 * 1000; //5分钟，要根据实际情况调整
        properties.put("org.quartz.jobStore.misfireThreshold", String.valueOf(misfireThreshold));
        //
        properties.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        return properties;
    }

}
