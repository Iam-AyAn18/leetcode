package org.neet.code.practice;

import java.util.HashMap;

/**
 * 325. Maximum Size Subarray Sum Equals k (LeetCode-style)
 * Medium
 *
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k.
 * If no such subarray exists, return 0.
 * A subarray is a contiguous part of the array.
 *
 * Example 1:
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: [1, -1, 5, -2] sums to 3 and has length 4.
 *
 * Example 2:
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: [-1, 2] sums to 1 and has length 2.
 *
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - -10^9 <= k <= 10^9
 *
 * Note: This file intentionally does not include a solution implementation. It contains a method
 * stub suitable for use in a coding prompt or exercise.
 */
public class MaximumSizeSubarraySumEqualsK {

    /**
     * Stub for the required function. Implementations should return the maximum length of a
     * contiguous subarray that sums to k, or 0 if no such subarray exists.
     *
     * Do NOT include a solution in this file if used as a prompt.
     */

    public int maxSubArrayLen(int[] nums, int k) {
        int maxLen=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int prefixSum =0;

        for(int i =0; i<nums.length; i++)
        {
            prefixSum+=nums[i];
            if(map.containsKey(prefixSum - k) && maxLen< i - map.get(prefixSum-k))
            {
                maxLen = i - map.get(prefixSum-k);
            }
            if(!map.containsKey(prefixSum)){
                map.put(prefixSum,i);
            }
        }
        return maxLen;
    }

    public int maxSubArrayLen2(int[] nums, int k) {
        int maxLen=0;
        for(int i = 0; i< nums.length; i++)
        {
            int sum =0;
            for(int j =i; j<nums.length; j++)
            {
                sum+= nums[j];
                if(sum == k && maxLen<j-i+1)
                    maxLen=j-i+1;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK solver = new MaximumSizeSubarraySumEqualsK();

        class TestCase {
            final int[] nums;
            final int k;
            final int expected;
            final String desc;
            TestCase(int[] nums, int k, int expected, String desc) { this.nums = nums; this.k = k; this.expected = expected; this.desc = desc; }
        }

        TestCase[] tests = new TestCase[] {
            new TestCase(new int[] {1, -1, 5, -2, 3}, 3, 4, "example1"),
            new TestCase(new int[] {-2, -1, 2, 1}, 1, 2, "example2"),
            new TestCase(new int[] {1,2,3}, 7, 0, "no subarray"),
            new TestCase(new int[] {1, -1, 1, -1, 3}, 3, 5, "mixed case"),
            new TestCase(new int[] {}, 0, 0, "empty array"),
            // additional edge cases
            new TestCase(new int[] {0,0,0,0}, 0, 4, "all zeros"),
            new TestCase(new int[] {3,3,3}, 6, 2, "repeating elements"),
            new TestCase(new int[] {1,2,3, -3, 3}, 3, 4, "mixed example that spans prefix repeats"),
            new TestCase(new int[] {1,2,3,4,5}, 15, 5, "whole array sum"),
        };

        for (int i = 0; i < tests.length; i++) {
            TestCase t = tests[i];
            System.out.println("Test #" + (i+1) + " - " + t.desc);
            System.out.print("Input nums = [");
            for (int j = 0; j < t.nums.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(t.nums[j]);
            }
            System.out.println("], k = " + t.k);
            System.out.println("Expected: " + t.expected);

            int actual;
            try {
                actual = solver.maxSubArrayLen(t.nums, t.k);
            } catch (Exception ex) {
                System.out.println("Actual:   threw exception: " + ex);
                System.out.println("Result:   FAIL\n");
                continue;
            }

            System.out.println("Actual:   " + actual);
            System.out.println(actual == t.expected ? "Result:   PASS\n" : "Result:   FAIL\n");
        }
    }
}
