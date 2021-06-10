package com.tangcheng.learning.util;

import com.tangcheng.learning.util.domain.bo.ModelHeaderBO;
import com.tangcheng.learning.util.domain.bo.ModelItemBO;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/9 5:53 下午
 * @see
 * @since
 */
public class ModelToMapUtilTest {

    @Test
    public void transfer2Map() {
        List<ModelItemBO> modelItemBOList = new ArrayList<>();
        List<Map<String, Object>> expectedItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ModelItemBO modelItemBO = new ModelItemBO();
            modelItemBO.setId((long) i);
            String name = "itemName_" + i;
            modelItemBO.setName(name);
            modelItemBOList.add(modelItemBO);
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("name", name);
            expectedItems.add(map);
        }
        ModelHeaderBO modelHeaderBO = new ModelHeaderBO();
        modelHeaderBO.setId(1L);
        modelHeaderBO.setName("headName_" + modelHeaderBO.getId());
        modelHeaderBO.setReceiverList(Arrays.asList("R1", "R2"));
        modelHeaderBO.setModelItemBOList(modelItemBOList);
        Map<String, Object> actual = ModelToMapUtil.transfer2Map(modelHeaderBO);
        System.out.println(actual);
        assertThat(actual).containsOnlyKeys("id", "name", "modelItemBOList", "receiverList");

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("id", 1);
        expectedMap.put("name", "headName_1");
        expectedMap.put("modelItemBOList", expectedItems);
        expectedMap.put("receiverList", Arrays.asList("R1", "R2"));
        assertThat(actual).containsAllEntriesOf(expectedMap);
    }


}