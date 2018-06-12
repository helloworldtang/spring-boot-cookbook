package com.tangcheng.learning.guava;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListsTest {

    @Test
    public void testListPartitionFunction() {
        List<Integer> sourceList = Lists.newArrayList(1, 2, 3, 4, 5);
        List<List<Integer>> partitionList = Lists.partition(sourceList, 2);
        assertThat(partitionList.size()).isEqualTo(3);

        sourceList = Lists.newArrayList();
        partitionList = Lists.partition(sourceList, 2);
        assertThat(partitionList.size()).isEqualTo(0);

        sourceList = Lists.newArrayList(1, 2, 3, 4);
        partitionList = Lists.partition(sourceList, 2);
        assertThat(partitionList.size()).isEqualTo(2);
    }


}
