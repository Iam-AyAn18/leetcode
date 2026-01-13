package org.neet.code.Easy.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 496. Next Greater Element I
 * Easy
 *
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 *
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 * Constraints:
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 *
 * NOTE: This file is a LeetCode-style stub. The solution implementation is intentionally left as a TODO.
 */
public class NextGreaterElementI {

    /**
     * Finds the next greater element for each element in nums1 based on nums2.
     * TODO: implement this method using monotonic stack + HashMap pattern.
     *
     * @param nums1 query array (subset of nums2)
     * @param nums2 reference array
     * @return array where result[i] is next greater element of nums1[i] in nums2, or -1 if none exists
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stk = new Stack<>();

        for(int num2 : nums2)
        {
            while(!stk.isEmpty() && stk.peek()<num2){
                map.put(stk.pop(),num2);
            }
            stk.push(num2);
        }
        while(!stk.isEmpty())
        {
            map.put(stk.pop(),-1);
        }
        for(int i =0; i<nums1.length; i++)
        {
            nums1[i]=map.get(nums1[i]);
        }

        return nums1;
    }

    // Test case record for structured testing
    record TestCase(int[] nums1, int[] nums2, int[] expected, String description) {}

    public static void main(String[] args) {
        NextGreaterElementI solution = new NextGreaterElementI();

        // Define test cases
        TestCase[] testCases = {
            // Example test cases from problem statement
            new TestCase(
                new int[]{4, 1, 2},
                new int[]{1, 3, 4, 2},
                new int[]{-1, 3, -1},
                "Example 1 - mixed results"
            ),
            new TestCase(
                new int[]{2, 4},
                new int[]{1, 2, 3, 4},
                new int[]{3, -1},
                "Example 2 - increasing sequence"
            ),

            // Edge cases
            new TestCase(
                new int[]{1},
                new int[]{1, 2},
                new int[]{2},
                "Single element with next greater"
            ),
            new TestCase(
                new int[]{2},
                new int[]{1, 2},
                new int[]{-1},
                "Single element at end (no next greater)"
            ),
            new TestCase(
                new int[]{1},
                new int[]{1},
                new int[]{-1},
                "Single element in both arrays"
            ),

            // All elements have next greater
            new TestCase(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4},
                new int[]{2, 3, 4},
                "All have next greater element"
            ),

            // No elements have next greater
            new TestCase(
                new int[]{4, 3, 2},
                new int[]{4, 3, 2, 1},
                new int[]{-1, -1, -1},
                "Decreasing sequence - no next greater"
            ),

            // Same element appears at different positions
            new TestCase(
                new int[]{1, 3},
                new int[]{1, 2, 3, 4},
                new int[]{2, 4},
                "Elements in order with gaps"
            ),

            // nums1 has elements in reverse order of nums2
            new TestCase(
                new int[]{3, 2, 1},
                new int[]{1, 2, 3},
                new int[]{-1, 3, 2},
                "Reverse order query"
            ),

            // Mountain pattern
            new TestCase(
                new int[]{2, 5, 3},
                new int[]{1, 2, 3, 5, 4},
                new int[]{3, -1, 5},
                "Mountain pattern in nums2"
            ),

            // nums1 queries last element
            new TestCase(
                new int[]{5},
                new int[]{1, 2, 3, 4, 5},
                new int[]{-1},
                "Query last element"
            ),

            // nums1 queries first element
            new TestCase(
                new int[]{1},
                new int[]{1, 5, 3, 2, 4},
                new int[]{5},
                "Query first element"
            ),

            // Multiple queries, mixed positions
            new TestCase(
                new int[]{1, 5, 2},
                new int[]{5, 1, 3, 2, 4},
                new int[]{3, -1, 4},
                "Multiple queries at different positions"
            ),

            // Large difference between elements
            new TestCase(
                new int[]{1, 100},
                new int[]{1, 50, 100, 200},
                new int[]{50, 200},
                "Large value differences"
            ),

            // All same next greater
            new TestCase(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 10},
                new int[]{2, 3, 10},
                "Sequential with large final element"
            ),

            // Zigzag pattern
            new TestCase(
                new int[]{2, 4, 1},
                new int[]{1, 2, 4, 3},
                new int[]{4, -1, 2},
                "Zigzag pattern"
            ),

            // Maximum constraints
            new TestCase(
                new int[]{10000, 9999},
                new int[]{9999, 10000},
                new int[]{-1, 10000},
                "Maximum values - descending query order"
            ),

            // Critical test case - demonstrates why 'while' is needed instead of 'if'
            new TestCase(
                new int[]{1, 3, 5, 2, 4},
                new int[]{6, 5, 4, 3, 2, 1, 7},
                new int[]{7, 7, 7, 7, 7},
                "All elements before large element - needs while loop"
            )
        };

        // Run all test cases
        System.out.println("Running Next Greater Element I Tests...\n");
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[] result = solution.nextGreaterElement(tc.nums1, tc.nums2);
            boolean isPass = arrayEquals(result, tc.expected);

            if (isPass) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test #%d - %s%n", i + 1, tc.description);
            System.out.printf("Input nums1 = %s%n", arrayToString(tc.nums1));
            System.out.printf("Input nums2 = %s%n", arrayToString(tc.nums2));
            System.out.printf("Expected: %s%n", arrayToString(tc.expected));
            System.out.printf("Actual:   %s%n", arrayToString(result));
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

    private static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return arr1 == arr2;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}

