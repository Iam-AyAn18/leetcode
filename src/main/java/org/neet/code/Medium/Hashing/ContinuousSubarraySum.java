package org.neet.code.Medium.Hashing;

import java.util.HashMap;

/**
 * 523. Continuous Subarray Sum
 * Medium
 *
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 *
 * A good subarray is a subarray where:
 * - its length is at least two, and
 * - the sum of the elements of the subarray is a multiple of k.
 *
 * Note that:
 * - A subarray is a contiguous part of the array.
 * - An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 *
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 *
 * NOTE: This file is a LeetCode-style stub. The solution implementation is intentionally left as a TODO.
 */
public class ContinuousSubarraySum {

    /**
     * Returns true if nums has a good subarray (length >= 2, sum is multiple of k).
     * TODO: implement this method using prefix sum with modulo pattern.
     *
     * @param nums input array
     * @param k divisor value
     * @return true if a good subarray exists, false otherwise
     */
    public boolean checkSubarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        int maxLen = 0;

        for(int i = 0; i<nums.length; i++)
        {
            sum+= nums[i];
            if(!map.containsKey(sum%k))
            {
                map.put(sum%k,i);
            }
            else
            {
                maxLen = Math.max(maxLen,i-map.get(sum%k));
            }
        }

        return maxLen > 1;
    }

    // Test case record for structured testing
    record TestCase(int[] nums, int k, boolean expected, String description) {}

    public static void main(String[] args) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();

        // Define test cases
        TestCase[] testCases = {

                new TestCase(
                        new int[]{0, 1, 0},
                        1,
                        false,
                        "Zero at boundaries - no valid subarray with sum multiple of 1"
                ),

                // Consecutive elements equal to k
                new TestCase(
                        new int[]{5, 2, 4},
                        3,
                        true,
                        "[2,4] sums to 6 (2*3)"
                ),

                // Negative-like behavior (all elements less than k)
                new TestCase(
                        new int[]{1, 2, 3},
                        5,
                        true,
                        "Small numbers - [2,3] sums to 5"
                ),


            // Example test cases from problem statement
            new TestCase(
                new int[]{23, 2, 4, 6, 7},
                6,
                true,
                "Example 1 - [2,4] sums to 6"
            ),
            new TestCase(
                new int[]{23, 2, 6, 4, 7},
                6,
                true,
                "Example 2 - entire array sums to 42 (7*6)"
            ),
            new TestCase(
                new int[]{23, 2, 6, 4, 7},
                13,
                false,
                "Example 3 - no valid subarray"
            ),

            // Edge cases
            new TestCase(
                new int[]{0, 0},
                1,
                true,
                "Two zeros - sum is 0 (multiple of any k)"
            ),
            new TestCase(
                new int[]{5, 0, 0},
                3,
                true,
                "Contains zeros - [0,0] sums to 0"
            ),

            // Same remainder cases (important for modulo pattern)
            new TestCase(
                new int[]{23, 2, 4, 6, 6},
                7,
                true,
                "Same remainder - subarray between same remainders"
            ),

            // Minimum length constraint
            new TestCase(
                new int[]{5},
                5,
                false,
                "Single element - length < 2"
            ),
            new TestCase(
                new int[]{1, 0},
                2,
                false,
                "Sum is 1, not multiple of 2"
            ),

            // Large numbers
            new TestCase(
                new int[]{1000000000, 1000000000},
                1000000000,
                true,
                "Large numbers - sum is 2*k"
            ),

            // Multiple valid subarrays
            new TestCase(
                new int[]{2, 4, 3},
                6,
                true,
                "Multiple valid - [2,4] sums to 6"
            ),

            // No valid subarray
            new TestCase(
                new int[]{1, 2, 3},
                7,
                false,
                "No valid subarray"
            ),

            // All zeros
            new TestCase(
                new int[]{0, 0, 0},
                5,
                true,
                "All zeros - any subarray works"
            ),

            // k = 1 (special case - any sum >= 2 elements is valid)
            new TestCase(
                new int[]{1, 1},
                1,
                true,
                "k=1 - any sum is multiple of 1"
            ),

            // Wraparound case (entire array)
            new TestCase(
                new int[]{1, 2, 3, 4, 5},
                15,
                true,
                "Entire array sums to 15"
            )
        };

        // Run all test cases
        System.out.println("Running Continuous Subarray Sum Tests...\n");
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            boolean result = solution.checkSubarraySum(tc.nums, tc.k);
            boolean isPass = result == tc.expected;

            if (isPass) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test #%d - %s%n", i + 1, tc.description);
            System.out.printf("Input nums = %s, k = %d%n", arrayToString(tc.nums), tc.k);
            System.out.printf("Expected: %s%n", tc.expected);
            System.out.printf("Actual:   %s%n", result);
            System.out.printf("Result:   %s%n", isPass ? "PASS ✓" : "FAIL ✗");
            System.out.println();
        }

        // Summary
        System.out.println("=".repeat(50));
        System.out.printf("Total: %d | Passed: %d | Failed: %d%n",
                         testCases.length, passed, failed);
        System.out.println("=".repeat(50));
    }

    private static String arrayToString(int[] arr) {
        if (arr == null || arr.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

