package com.tangcheng.learning.util.domain.bo;

import lombok.Data;

import java.util.Map;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/10 2:11 下午
 * @see
 * @since
 */
@Data
public class ModelPlanBO {

    private Long id;

    private String name;

    private Map<String, Object> map;

}
