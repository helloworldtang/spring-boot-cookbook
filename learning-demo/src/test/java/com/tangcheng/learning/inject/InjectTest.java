package com.tangcheng.learning.inject;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by tangcheng on 10/15/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InjectTest {

    @Value("classpath:sourceData.txt")
    private Resource resource;

    @Test
    public void injectFileTest() throws IOException {
        assertThat(resource, notNullValue());
        String canonicalPath = resource.getFile().getCanonicalPath();
        System.out.println(canonicalPath);
        List<String> list = IOUtils.readLines(resource.getInputStream(), "utf-8");
        assertThat(list, hasItems(is("123"), is("456")));
        for (String s : list) {
            System.out.println(s);
        }
    }

}
