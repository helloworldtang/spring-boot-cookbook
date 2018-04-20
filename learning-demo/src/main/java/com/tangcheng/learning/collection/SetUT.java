package com.tangcheng.learning.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tangcheng
 * 2018/04/20
 */
@Slf4j
public class SetUT {

    @Test
    public void Set_ContainsAll_And_RemoveAll_Test() {
        Set<Integer> set1 = new HashSet<Integer>() {
            {
                add(1);
                add(2);
                add(3);
                add(2);
            }
        };

        Set<Integer> set2 = new HashSet<Integer>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
            }
        };

        log.info("origin set1 :{}", set1);
        log.info("origin set2 :{}", set2);
        int expectSet1Size = set1.size();
        int expectSet2Size = set2.size();

        boolean containsAll = set1.containsAll(set2);
        assertThat(containsAll).isFalse();
        assertThat(set1).size().isEqualTo(expectSet1Size);
        assertThat(set2).size().isEqualTo(expectSet2Size);
        log.info("set1 :{}", set1);
        log.info("set2 :{}", set2);

        boolean removeAll = set1.removeAll(set2);
        assertThat(removeAll).isTrue();
        assertThat(set1).isEmpty();
        assertThat(set2).size().isEqualTo(expectSet2Size);
    }


}
