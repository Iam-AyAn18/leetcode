package org.neet.code.practice;

import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K (LeetCode-style)
 * Medium
 *
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 *
 * Note: This file contains the problem statement and a method stub only. Do NOT include the solution here.
 */
public class SubarraySumEqualsK {

    /**
     * Returns the total number of continuous subarrays whose sum equals to k.
     * TODO: implement this method. No solution should be provided in this file if used as a prompt.
     *
     * @param nums input array
     * @param k target sum
     * @return number of subarrays with sum equal to k
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for(int num : nums){
            prefixSum += num;
            if(map.getOrDefault(prefixSum-k,0)>0)
            {
                count+= map.get(prefixSum-k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0)+1);
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for(int i =0; i< nums.length; i++)
        {
            int sum = 0;
            for(int j =i; j< nums.length; j++)
            {
                //System.out.print(nums[j]+"+");
                sum += nums[j];
                if(sum == k)
                {
                    //System.out.println(true);
                    count++;
                }
            }
            //System.out.println(false);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK solver = new SubarraySumEqualsK();

        // small local TestCase class
        class TestCase {
            final int[] nums;
            final int k;
            final int expected;
            final String desc;

            TestCase(int[] nums, int k, int expected, String desc) {
                this.nums = nums;
                this.k = k;
                this.expected = expected;
                this.desc = desc;
            }
        }

        TestCase[] tests = new TestCase[] {
            new TestCase(new int[] {1,1,1}, 2, 2, "example1"),
            new TestCase(new int[] {1,2,3}, 3, 2, "example2"),
            new TestCase(new int[] {1}, 0, 0, "single element not matching"),
            new TestCase(new int[] {1,-1,1}, 1, 3, "negatives and positives"),
            new TestCase(new int[] {3,4,7,2,-3,1,4,2}, 7, 4, "multiple hits"),
            // This test exposes the bug: multiple zero subarrays starting at same index are missed.
            new TestCase(new int[] {0,0,0}, 0, 6, "all zeros k=0 - should count all subarrays"),
        };

        for (int i = 0; i < tests.length; i++) {
            TestCase t = tests[i];
            System.out.println("Test #" + (i+1) + " - " + t.desc);
            System.out.print("Input nums = [");
            for (int j = 0; j < t.nums.length; j++) {
                if (j > 0) System.out.print(",");
                System.out.print(t.nums[j]);
            }
            System.out.println("], k = " + t.k);
            System.out.println("Expected: " + t.expected);

            try {
                int actual = solver.subarraySum(t.nums, t.k);
                System.out.println("Actual:   " + actual);
                if (actual == t.expected) {
                    System.out.println("Result:   PASS\n");
                } else {
                    System.out.println("Result:   FAIL\n");
                }

                if (actual == 0 && t.expected != 0) {
                    System.out.println("Warning: actual returned 0 while expected is non-zero.\n");
                }
            } catch (UnsupportedOperationException ex) {
                System.out.println("Actual:   NOT_IMPLEMENTED (subarraySum is a stub)");
                System.out.println("Result:   SKIPPED\n");
            }
        }
    }
}
