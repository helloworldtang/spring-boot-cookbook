package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.learning.business.component.TimeCostStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/7/27 10:47 PM
 * @Description:
 */
@RestController
@Api(tags = "测试TimeCostStatistics注解")
public class TimeCostStatisticsController {

    @Autowired
    private TimeCostStatisticsService timeCostStatisticsService;

    @ApiOperation("注解加在接口上的方法【没有生效】")
    @GetMapping("/time_cost_statistics/interface")
    public String runWhenTimeCostStatisticsOnInterface() {
        timeCostStatisticsService.runWhenTimeCostStatisticsOnInterface();
        return "success";
    }


    @ApiOperation("注解加在实现类上的方法【生效了】")
    @GetMapping("/time_cost_statistics/impl")
    public String runWhenTimeCostStatisticsOnImpl() {
        timeCostStatisticsService.runWhenTimeCostStatisticsOnImpl();
        return "success";
    }

}
