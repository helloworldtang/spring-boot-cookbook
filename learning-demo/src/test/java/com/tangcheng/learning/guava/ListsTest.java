package com.tangcheng.learning.guava;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.springframework.util.StopWatch;

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


    /**
     * https://mp.weixin.qq.com/s/VGtH_DXI6paogOekrodixA
     */
    @Test
    public void testBloomFilterWithGuava() {
        StopWatch stopWatch = new StopWatch("guavaBloomFilterDemo");
        stopWatch.start("init");
        int num = 10000000;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), num, 0.01);
        for (int i = 0; i < num; i++) {
            bloomFilter.put(i);
        }
        stopWatch.stop();
        stopWatch.start("check-1");
        assertThat(bloomFilter.mightContain(1)).isTrue();
        stopWatch.stop();

        stopWatch.start("check-9999");
        assertThat(bloomFilter.mightContain(9999)).isTrue();
        stopWatch.stop();

        stopWatch.start("check-1234567");
        assertThat(bloomFilter.mightContain(1234567)).isTrue();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

//        -----------------------------------------
//        ms     %     Task name
//        -----------------------------------------
//        04443  099%  init
//        00037  001%  check-1
//        00000  000%  check-9999
//        00000  000%  check-1234567

    }


}
