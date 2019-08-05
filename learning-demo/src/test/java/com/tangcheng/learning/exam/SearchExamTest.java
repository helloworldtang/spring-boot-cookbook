package com.tangcheng.learning.exam;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/18/2019 11:49 PM
 */
public class SearchExamTest {

    @Test
    public void binarySearch() {
        SearchExam searchExam = new SearchExam();
        int resultIdx = searchExam.binarySearch(Arrays.asList(1, 2, 3, 4), 4);
        assertThat(resultIdx).isEqualTo(3);
        resultIdx = searchExam.binarySearch(Arrays.asList(1, 2, 3, 5, 4), 4);
        assertThat(resultIdx).isEqualTo(3);
        resultIdx = searchExam.binarySearch(Arrays.asList(1, 2, 3, 5, 6), 4);
        assertThat(resultIdx).isEqualTo(-1);
    }

    @Test
    public void bubbleSort() {
        SearchExam searchExam = new SearchExam();
        List<Integer> sourceList = Arrays.asList(1, 3, 4, 2, 5, 9);
        searchExam.bubbleSort(sourceList);
        assertThat(sourceList.get(1)).isEqualTo(2);
        System.out.println(sourceList);
    }


}