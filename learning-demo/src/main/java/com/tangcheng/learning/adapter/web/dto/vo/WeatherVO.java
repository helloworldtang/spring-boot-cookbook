package com.tangcheng.learning.adapter.web.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 7/25/2018 7:31 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherVO {
    /**
     * 城市
     */
    private String cityName;
    /**
     * 天气情况
     */
    private String weatherDetail;
}
