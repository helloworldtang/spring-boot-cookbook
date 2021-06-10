package com.tangcheng.learning.util.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/9 5:48 下午
 * @see
 * @since
 */
@Data
public class ModelHeaderBO {

    private Long id;

    private String name;

    private List<ModelItemBO> modelItemBOList;

    private List<String> receiverList;

}
