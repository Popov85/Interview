package com.interview.problems.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem #283: Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements
 * Note that you must do this in-place without making a copy of the array.
 * @ see <a href = "https://leetcode.com/problems/move-zeroes/description/">info</>
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] ints = new MoveZeroes().moveZeroes(new int[]{1, 2, 3, 0, 5, 0, 0, 7, 8});
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        System.out.println("Modified array is = "+ list);
    }

    public int[] moveZeroes(int[] nums) {

        // [4,3,0,0,5,0,1,0]
        // Idea is to count zeroes along iterating the array
        // As soon as non-zero element is met, move it back by countZero positions.

        int countZero = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                countZero++;
            } else {
                if (countZero != 0) {
                    // Move current element back to [current - countZero position]
                    nums[i - countZero] = nums[i];
                    nums[i] = 0;
                }
            }
        }
        return nums;
    }
}
