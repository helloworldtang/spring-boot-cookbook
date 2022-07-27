package com.tangcheng.learning.business.component;

import com.tangcheng.component.cost.aop.annotation.TimeCostStatistics;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/7/27 10:49 PM
 * @Description:
 */
public interface TimeCostStatisticsService {

    @TimeCostStatistics(bizFlag = "注解加在接口上")
    void runWhenTimeCostStatisticsOnInterface();


    void runWhenTimeCostStatisticsOnImpl();


}
