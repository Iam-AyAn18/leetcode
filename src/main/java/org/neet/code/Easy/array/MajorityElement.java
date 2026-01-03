package org.neet.code.Easy.array;

/*
 * 169. Majority Element
 * Easy
 *
 * Problem:
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * The input is generated such that a majority element will exist in the array.
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */

import java.util.Arrays;

public class MajorityElement {
    public static int majorityElement(int[] nums) {
        nums = Arrays.stream(nums).sorted().toArray();
        return nums[nums.length/2];
    }

    // Boyer-Moore Voting Algorithm (O(n) time, O(1) space)
    public static int majorityElement2(int[] nums) {

        int count =0, n=0;

        for(int num : nums)
        {
             if(count==0)
             {
                 n = num;
             }
             count+= (n==num) ? 1 : -1;

        }
        return n;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3};
        System.out.println("Result 1: " + majorityElement(nums1)); // Output: 3
        System.out.println("Boyer-Moore 1: " + majorityElement2(nums1));

        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Result 2: " + majorityElement(nums2)); // Output: 2
        System.out.println("Boyer-Moore 2: " + majorityElement2(nums2));

        int[] nums3 = {1, 2, 2, 2, 3, 4, 2};
        System.out.println("Result 3: " + majorityElement(nums3)); // Output: 2
        System.out.println("Boyer-Moore 3: " + majorityElement2(nums3));
    }
}
