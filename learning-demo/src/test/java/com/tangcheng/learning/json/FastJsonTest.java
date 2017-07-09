package com.tangcheng.learning.json;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by tangcheng on 7/9/2017.
 */
public class FastJsonTest {

    @Test
    public void toJSONString() {

        Object objectNull = null;
        assertThat("is null", null, isEmptyOrNullString());
        String jsonString = JSON.toJSONString(objectNull);

        System.out.println("will be false:" + Objects.equals(jsonString, null));
        System.out.println("will be false:" + StringUtils.isBlank(jsonString));

        assertThat("is string with 'null'", "null", is(jsonString));

    }

}
