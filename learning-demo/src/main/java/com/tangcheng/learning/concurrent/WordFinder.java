package com.tangcheng.learning.concurrent;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public class WordFinder {

    private static Set<String> allWords = new HashSet<>();

    static {
        allWords.add("ali");
        allWords.add("alimama");
        allWords.add("alibaba");
        allWords.add("aliyun");
        allWords.add("cainiao");

    }

    private List<String> dictWordList = new ArrayList<>(allWords);


    private static final int MULTI_THREAD_THRESHOLD = 2;


    public List<String> findAllWordWithMultiThread(String word) throws ExecutionException, InterruptedException {
        if (allWords.size() < MULTI_THREAD_THRESHOLD) {
            return findPartWordWithForeach(dictWordList, word);
        }
        List<List<String>> partitionList = Lists.partition(dictWordList, MULTI_THREAD_THRESHOLD);

        List<CompletableFuture<List<String>>> completableFutureList = partitionList.stream().map(partList -> (
                CompletableFuture.supplyAsync(() -> {
                    return findPartWordWithForeach(partList, word);
                }))
        ).collect(Collectors.toList());
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();
        List<String> allFindResult = new ArrayList<>();
        for (CompletableFuture<List<String>> completableFuture : completableFutureList) {
            allFindResult.addAll(completableFuture.get());
        }
        return allFindResult;
    }


    public List<String> findPartWordWithForeach(List<String> sourceDict, String word) {
        if (StringUtils.isBlank(word)) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (String dictWord : sourceDict) {
            if (dictWord.contains(word)) {
                log.info("match:{} source:{}",dictWord,word);
                result.add(dictWord);
            }
        }
        return result;
    }


}
