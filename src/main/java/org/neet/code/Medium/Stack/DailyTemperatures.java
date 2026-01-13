package org.neet.code.Medium.Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 739. Daily Temperatures
 * Medium
 *
 * Given an array of integers temperatures represents the daily temperatures, return an array answer
 * such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 * Constraints:
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 * NOTE: This file is a LeetCode-style stub. The solution implementation is intentionally left as a TODO.
 */
public class DailyTemperatures {

    /**
     * Calculates the number of days until a warmer temperature for each day.
     * TODO: implement this method using monotonic stack.
     *
     * Approach: Use a monotonic decreasing stack to find the next warmer day efficiently.
     * - Iterate through temperatures
     * - For each temperature, pop all smaller temperatures from stack (they found their warmer day)
     * - Push current index to stack
     * - Time: O(n), Space: O(n)
     *
     * @param temperatures array of daily temperatures
     * @return array where answer[i] is days to wait for a warmer temperature
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stk = new Stack<>();

        for(int i = 0; i < n; i++)
        {
            while(!stk.isEmpty() && temperatures[i] > temperatures[stk.peek()])
            {
                int prevIndex = stk.pop();
                result[prevIndex] = i - prevIndex;
            }
            stk.push(i);
        }

        return result;
    }

    // Test case record for structured testing
    record TestCase(int[] temperatures, int[] expected, String description) {}

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();

        // Define test cases
        TestCase[] testCases = {
            // Example test cases from problem statement
            new TestCase(
                new int[]{73, 74, 75, 71, 69, 72, 76, 73},
                new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                "Example 1 - mixed temperatures"
            ),
            new TestCase(
                new int[]{30, 40, 50, 60},
                new int[]{1, 1, 1, 0},
                "Example 2 - strictly increasing"
            ),
            new TestCase(
                new int[]{30, 60, 90},
                new int[]{1, 1, 0},
                "Example 3 - strictly increasing with gaps"
            ),

            // Edge cases
            new TestCase(
                new int[]{30},
                new int[]{0},
                "Single temperature"
            ),
            new TestCase(
                new int[]{30, 30},
                new int[]{0, 0},
                "Two identical temperatures"
            ),
            new TestCase(
                new int[]{100, 99, 98, 97},
                new int[]{0, 0, 0, 0},
                "Strictly decreasing - no warmer days"
            ),
            new TestCase(
                new int[]{50, 40, 60},
                new int[]{2, 1, 0},
                "Valley pattern"
            ),

            // Multiple same temperatures
            new TestCase(
                new int[]{40, 40, 40, 50},
                new int[]{3, 2, 1, 0},
                "Multiple identical then warmer"
            ),
            new TestCase(
                new int[]{50, 40, 40, 40},
                new int[]{0, 0, 0, 0},
                "High then multiple identical"
            ),

            // Peak pattern
            new TestCase(
                new int[]{30, 50, 40},
                new int[]{1, 0, 0},
                "Peak in middle"
            ),
            new TestCase(
                new int[]{30, 40, 50, 40, 30},
                new int[]{1, 1, 0, 0, 0},
                "Peak then descending"
            ),

            // Zigzag patterns
            new TestCase(
                new int[]{30, 50, 40, 60},
                new int[]{1, 2, 1, 0},
                "Zigzag up"
            ),
            new TestCase(
                new int[]{60, 40, 50, 30},
                new int[]{0, 1, 0, 0},
                "Zigzag down"
            ),

            // Long wait times
            new TestCase(
                new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70},
                new int[]{8, 1, 5, 4, 3, 2, 1, 1, 0, 0},
                "Long wait for warmer day"
            ),

            // Multiple peaks
            new TestCase(
                new int[]{40, 50, 40, 60, 50, 70},
                new int[]{1, 2, 1, 2, 1, 0},
                "Multiple peaks"
            ),

            // Boundary values
            new TestCase(
                new int[]{30, 30, 30, 100},
                new int[]{3, 2, 1, 0},
                "Min temp to max temp"
            ),
            new TestCase(
                new int[]{100, 100, 100, 30},
                new int[]{0, 0, 0, 0},
                "Max temp throughout"
            ),

            // Large differences
            new TestCase(
                new int[]{30, 31, 32, 100, 99, 98},
                new int[]{1, 1, 1, 0, 0, 0},
                "Gradual then sudden spike"
            ),

            // Alternating pattern
            new TestCase(
                new int[]{50, 40, 50, 40, 50},
                new int[]{0, 1, 0, 1, 0},
                "Alternating high-low"
            ),

            // Complex scenario
            new TestCase(
                new int[]{55, 38, 53, 81, 61, 93, 97, 32, 43, 78},
                new int[]{3, 1, 1, 2, 1, 1, 0, 1, 1, 0},
                "Complex mixed temperatures"
            )
        };

        // Run all test cases
        System.out.println("Running Daily Temperatures Tests...\n");
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[] result = solution.dailyTemperatures(tc.temperatures);
            boolean isPass = Arrays.equals(result, tc.expected);

            if (isPass) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test #%d - %s%n", i + 1, tc.description);
            System.out.printf("Input temperatures = %s%n", arrayToString(tc.temperatures));
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
}

