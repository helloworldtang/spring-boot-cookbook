package com.tangcheng.learning.exam;

import java.util.HashMap;
import java.util.Map;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/4 9:14 上午
 * @see
 * @since
 */
public class TwoSumSolutionExam {

    public int[] twoSumFindFirst(int[] nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int leftNum = target - currentNum;
            if (valueToIndex.containsKey(leftNum)) {
                return new int[]{i, valueToIndex.get(leftNum)};
            }
            valueToIndex.put(currentNum, i);
        }
        return null;
    }


}
