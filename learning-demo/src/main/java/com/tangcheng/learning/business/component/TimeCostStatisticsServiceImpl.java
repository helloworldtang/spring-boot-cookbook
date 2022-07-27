package com.tangcheng.learning.business.component;

import com.tangcheng.component.cost.aop.annotation.TimeCostStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/7/27 10:49 PM
 * @Description:
 */
@Service
@Slf4j
public class TimeCostStatisticsServiceImpl implements TimeCostStatisticsService {

    @Override
    public void runWhenTimeCostStatisticsOnInterface() {
        log.info("runWhenTimeCostStatisticsOnInterface run");
    }

    @TimeCostStatistics(bizFlag = "注解加在实现类上")
    @Override
    public void runWhenTimeCostStatisticsOnImpl() {
        log.info("runWhenTimeCostStatisticsOnImpl run");
    }


}
