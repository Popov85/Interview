package com.interview.problems.hashtable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * LeetCode problem <a href="https://leetcode.com/problems/two-sum/"></a>
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] calc = twoSum.calc(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Result = "+ Arrays.toString(calc));

    }

    public int[] calc(int[] nums, int target) {
        // Built a map of element and position in an array

        Map<Integer, Integer> helper = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            helper.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (helper.containsKey(target - nums[i])) {
                return new int[]{i, helper.get(target - nums[i])};
            }
        }
        return new int[]{};
    }
}
