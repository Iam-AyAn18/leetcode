package org.neet.code.Medium.Math;/*
 * 1390. Four Divisors
 * Medium
 *
 * Given an integer array nums, return the sum of divisors of the integers in that array
 * that have exactly four divisors. If there is no such integer in the array, return 0.
 *
 * Example 1:
 * Input: nums = [21,4,7]
 * Output: 32
 *
 * Example 2:
 * Input: nums = [21,21]
 * Output: 64
 *
 * Example 3:
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FourDivisors {

    /**
     * Returns the sum of divisors for numbers in nums that have exactly four divisors.
     *
     * @param nums input array
     * @return placeholder 0 until implemented
     */
    public int sumFourDivisors(int[] nums) {

        int sum = 0;
        for (int num : nums) {
           sum+= countDiv(num);
        }
        return sum;
    }

    private int countDiv(int num) {
        if (num <= 1) return 0;
        HashSet<Integer> sets = new HashSet<>();
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sets.add(i);
                sets.add(num / i);
            }
            if(sets.size()>4)
                return 0;
        }
        int sum = 0;
        if (sets.size() == 4) {
            for (int set : sets) {
                sum += set;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        FourDivisors sol = new FourDivisors();

        int[] example1 = {21, 4, 7};
        System.out.println("Example1 (expected 32): " + sol.sumFourDivisors(example1));

        int[] example2 = {21, 21};
        System.out.println("Example2 (expected 64): " + sol.sumFourDivisors(example2));

        int[] example3 = {1, 2, 3, 4, 5};
        System.out.println("Example3 (expected 0): " + sol.sumFourDivisors(example3));
    }
}

