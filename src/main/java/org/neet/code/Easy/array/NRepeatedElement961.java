package org.neet.code.Easy.array;

/*
 * 961. N-Repeated Element in Size 2N Array
 * Easy
 *
 * Problem:
 * You are given an integer array nums with the following properties:
 *
 * nums.length == 2 * n.
 * nums contains n + 1 unique elements.
 * Exactly one element of nums is repeated n times.
 * Return the element that is repeated n times.
 *
 * Example 1:
 * Input: nums = [1,2,3,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,1,2,5,3,2]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 * Constraints:
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 10^4
 * nums contains n + 1 unique elements and one of them is repeated exactly n times
 */

import java.util.HashMap;
import java.util.HashSet;

public class NRepeatedElement961 {
    public static int repeatedNTimes(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums)
        {
            map.put(num, map.getOrDefault(num,0)+1);
            if(map.get(num) == nums.length/2)
                return num;
        }
        return -1;
    }

    public static int repeatedNTimes2(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for(int num : nums)
        {
            if(!set.add(num))
                return num;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 3};
        int[] test2 = {2, 1, 2, 5, 3, 2};
        int[] test3 = {5, 1, 5, 2, 5, 3, 5, 4};

        System.out.println("Test 1: " + repeatedNTimes(test1)); // Expected: 3
        System.out.println("Test 2: " + repeatedNTimes(test2)); // Expected: 2
        System.out.println("Test 3: " + repeatedNTimes(test3)); // Expected: 5
        System.out.println("---------------------------------------");
        System.out.println("Test 1: " + repeatedNTimes2(test1)); // Expected: 3
        System.out.println("Test 2: " + repeatedNTimes2(test2)); // Expected: 2
        System.out.println("Test 3: " + repeatedNTimes2(test3)); // Expected: 5
    }
}

