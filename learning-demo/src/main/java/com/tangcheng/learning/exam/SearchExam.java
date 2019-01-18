package com.tangcheng.learning.exam;

import java.util.Collections;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/18/2019 11:40 PM
 */
public class SearchExam {

    /**
     * 二分查找
     *
     * @param sourceList
     * @param targetNum
     * @return
     */
    public int binarySearch(List<Integer> sourceList, Integer targetNum) {
        if (sourceList == null || sourceList.isEmpty()) {
            return -1;
        }
        Collections.sort(sourceList);
        int start = 0, end = sourceList.size() - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            Integer midNum = sourceList.get(mid);
            if (midNum > targetNum) {
                end = mid - 1;
            } else if (midNum < targetNum) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public void bubbleSort(List<Integer> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return;
        }
        for (int i = 0; i < sourceList.size(); i++) {
            for (int j = 0; j < sourceList.size() - 1 - i; j++) {
                if (sourceList.get(j) > sourceList.get(j + 1)) {
                    int tmp = sourceList.get(j);
                    sourceList.set(j, sourceList.get(j + 1));
                    sourceList.set(j + 1, tmp);
                }
            }
        }
    }

}
