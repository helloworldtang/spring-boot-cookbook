package com.tangcheng.learning.util.template;

import com.alibaba.fastjson.JSON;
import com.tangcheng.learning.util.ModelToMapUtil;
import com.tangcheng.learning.util.domain.bo.ModelHeaderBO;
import com.tangcheng.learning.util.domain.bo.ModelPlanBO;
import org.junit.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/10 11:55 上午
 * @see
 * @since
 */
public class ThymeleafEngineTest {

    @Test
    public void given_Model_map_when_thymeleaf_then_process() {
        ModelHeaderBO modelHeaderBO = new ModelHeaderBO();
        modelHeaderBO.setId(1L);
        modelHeaderBO.setName("headName_" + modelHeaderBO.getId());
        modelHeaderBO.setReceiverList(Arrays.asList("R1", "R2"));
        ModelPlanBO modelPlanBO = new ModelPlanBO();
        modelPlanBO.setId(100L);
        modelPlanBO.setName("planName");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mapName");
        modelPlanBO.setMap(map);
        modelHeaderBO.setModelPlanBO(modelPlanBO);

        Map<String, Object> variables = ModelToMapUtil.transfer2Map(modelHeaderBO);
        SpringTemplateEngine thymeleafEngine = new SpringTemplateEngine();
        String template = "<div th:utext=\"|map:${modelPlanBO.map.name} name:${modelPlanBO.name} id:${modelPlanBO.id}|\"></div>";
        Context context = new Context();
        context.setVariables(variables);
        String actual = thymeleafEngine.process(template, context);
        assertThat(actual).isEqualTo("<div>map:mapName name:planName id:100</div>");
    }

    @Test
    public void given_Model_object_when_thymeleaf_then_process() {
        ModelHeaderBO modelHeaderBO = new ModelHeaderBO();
        modelHeaderBO.setId(1L);
        modelHeaderBO.setName("headName_" + modelHeaderBO.getId());
        modelHeaderBO.setReceiverList(Arrays.asList("R1", "R2"));
        ModelPlanBO modelPlanBO = new ModelPlanBO();
        modelPlanBO.setId(100L);
        modelPlanBO.setName("planName");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mapName");
        modelPlanBO.setMap(map);
        modelHeaderBO.setModelPlanBO(modelPlanBO);

        SpringTemplateEngine thymeleafEngine = new SpringTemplateEngine();
        String template = "<div th:utext=\"|map:${modelPlanBO.map.name} name:${modelPlanBO.name} id:${modelPlanBO.id}|\"></div>";
        Context context = new Context();
        context.setVariable("modelPlanBO", JSON.parse(JSON.toJSONString(modelPlanBO)));
        String actual = thymeleafEngine.process(template, context);
        assertThat(actual).isEqualTo("<div>map:mapName name:planName id:100</div>");
        System.out.println(actual);
    }

    @Test
    public void given_Model_object_when_thymeleaf_springEL_then_process() {
        ModelHeaderBO modelHeaderBO = new ModelHeaderBO();
        modelHeaderBO.setId(1L);
        modelHeaderBO.setName("headName_" + modelHeaderBO.getId());
        modelHeaderBO.setReceiverList(Arrays.asList("R1", "R2"));
        ModelPlanBO modelPlanBO = new ModelPlanBO();
        modelPlanBO.setId(100L);
        modelPlanBO.setName("planName");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mapName");
        modelPlanBO.setMap(map);
        modelHeaderBO.setModelPlanBO(modelPlanBO);

        Map<String, Object> variables = ModelToMapUtil.transfer2Map(modelHeaderBO);
        System.out.println(variables);
        Context context = new Context();
        context.setVariables(variables);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        String process = templateEngine.process(" [(${name})] 数字: [(${modelPlanBO.id})]  <div th:text=\"|${modelPlanBO.name} modelPlanBO.id: #{modelPlanBO.id} id:${id}|\"> </div>", context);
        System.out.println(process);
    }


}
