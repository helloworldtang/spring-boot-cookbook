package com.tangcheng.learning.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;


public class WordFinderTest {

    @Test
    public void findAllWordWithMultiThread_Given_cai_Then_RETURN_cainiao() throws ExecutionException, InterruptedException {
        List<String> expected = Collections.singletonList("cainiao");
        WordFinder wordFinder = new WordFinder();
        List<String> actual = wordFinder.findAllWordWithMultiThread("cai");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void findAllWordWithMultiThread_Given_li_Then_RETURN_3() throws ExecutionException, InterruptedException {
        List<String> expected = Arrays.asList("ali", "alimama", "alibaba", "aliyun");
        WordFinder wordFinder = new WordFinder();
        List<String> actual = wordFinder.findAllWordWithMultiThread("li");
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }


}