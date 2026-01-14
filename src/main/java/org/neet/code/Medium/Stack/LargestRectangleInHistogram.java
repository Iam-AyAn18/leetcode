package org.neet.code.Medium.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 * Hard
 *
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 *
 * Constraints:
 * 1 <= heights.length <= 10^5
 * 0 <= heights[i] <= 10^4
 *
 * NOTE: This file is a LeetCode-style stub. The solution implementation is intentionally left as a TODO.
 */
public class LargestRectangleInHistogram {

    /**
     * Finds the area of the largest rectangle in the histogram.
     *
     * Approach: Use a monotonic increasing stack to track potential rectangles.
     * - Stack stores indices of bars in increasing height order
     * - When we encounter a shorter bar, pop taller bars and calculate their max rectangle area
     * - For each popped bar, the width extends from the bar after the new stack top to current position
     * - Time: O(n), Space: O(n)
     *
     * @param heights array of bar heights
     * @return maximum rectangle area
     */
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stk = new Stack<>();
        int result =0;
        for(int i =0; i<heights.length; i++)
        {
            while(!stk.isEmpty() && heights[i]<heights[stk.peek()])
            {
                int h = stk.pop();
                int w = i - (!stk.isEmpty() ? stk.peek() + 1 : 0);
                result = Math.max(result, heights[h]*w);
            }
            //result = Math.max(result, heights[i]);
            stk.push(i);
        }
        while(!stk.isEmpty())
        {
            int h = stk.pop();
            int w = heights.length - (!stk.isEmpty() ? stk.peek() + 1 : 0);
            result = Math.max(result, heights[h]*w);
        }
        return result;
    }

    // Test case record for structured testing
    record TestCase(int[] heights, int expected, String description) {}

    public static void main(String[] args) {
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();

        // Define test cases
        TestCase[] testCases = {
            // Example test cases from problem statement
            new TestCase(
                new int[]{2, 1, 5, 6, 2, 3},
                10,
                "Example 1 - mixed heights, rectangle in middle"
            ),
            new TestCase(
                new int[]{2, 4},
                4,
                "Example 2 - two bars"
            ),

            // Edge cases
            new TestCase(
                new int[]{1},
                1,
                "Single bar"
            ),
            new TestCase(
                new int[]{0},
                0,
                "Single bar with height 0"
            ),
            new TestCase(
                new int[]{5, 5, 5, 5},
                20,
                "All bars same height"
            ),

            // Strictly increasing
            new TestCase(
                new int[]{1, 2, 3, 4, 5},
                9,
                "Strictly increasing - rectangle at middle (3*3)"
            ),
            new TestCase(
                new int[]{2, 4, 6, 8},
                12,
                "Strictly increasing"
            ),

            // Strictly decreasing
            new TestCase(
                new int[]{5, 4, 3, 2, 1},
                9,
                "Strictly decreasing - rectangle at middle (3*3)"
            ),
            new TestCase(
                new int[]{8, 6, 4, 2},
                12,
                "Strictly decreasing"
            ),

            // Peak pattern
            new TestCase(
                new int[]{1, 3, 5, 3, 1},
                9,
                "Peak pattern - rectangle at peak (3*3)"
            ),
            new TestCase(
                new int[]{2, 5, 6, 5, 2},
                15,
                "Peak pattern - wide rectangle (5*3)"
            ),

            // Valley pattern
            new TestCase(
                new int[]{5, 2, 1, 2, 5},
                10,
                "Valley pattern - tall rectangles on sides"
            ),
            new TestCase(
                new int[]{6, 2, 5, 4, 5, 1, 6},
                12,
                "Valley pattern - complex"
            ),

            // Zeros in the middle
            new TestCase(
                new int[]{3, 0, 3},
                3,
                "Zero in middle - separates rectangles"
            ),
            new TestCase(
                new int[]{5, 5, 0, 5, 5},
                10,
                "Zero in middle - two separate rectangles"
            ),

            // Large single bar
            new TestCase(
                new int[]{10000},
                10000,
                "Maximum height single bar"
            ),
            new TestCase(
                new int[]{1, 10000, 1},
                10000,
                "Maximum height bar in middle"
            ),

            // Wide rectangle
            new TestCase(
                new int[]{2, 2, 2, 2, 2, 2, 2},
                14,
                "Wide uniform rectangle (2*7)"
            ),
            new TestCase(
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                10,
                "Very wide low rectangle (1*10)"
            ),

            // Mixed patterns
            new TestCase(
                new int[]{4, 2, 0, 3, 2, 5},
                6,
                "Mixed with zero"
            ),
            new TestCase(
                new int[]{3, 6, 5, 7, 4, 8, 1, 0},
                20,
                "Complex mixed pattern"
            ),

            // Zigzag
            new TestCase(
                new int[]{1, 5, 1, 5, 1, 5},
                6,
                "Zigzag pattern"
            ),
            new TestCase(
                new int[]{5, 1, 5, 1, 5, 1},
                8,
                "Zigzag starting high"
            ),

            // Real-world scenarios
            new TestCase(
                new int[]{2, 1, 2},
                3,
                "Small valley"
            ),
            new TestCase(
                new int[]{1, 2, 3, 2, 1},
                6,
                "Symmetric pyramid"
            ),
            new TestCase(
                new int[]{6, 7, 5, 2, 4, 5, 9, 3},
                16,
                "Random distribution"
            ),
                new TestCase(
                        new int[]{6,1,1},
                        6,
                        "Random distribution"
                )
        };

        // Run all test cases
        System.out.println("Running Largest Rectangle in Histogram Tests...\n");
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            if(i==5)
                System.out.print("");
            TestCase tc = testCases[i];
            int result = solution.largestRectangleArea(tc.heights);
            boolean isPass = result == tc.expected;

            if (isPass) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test #%d - %s%n", i + 1, tc.description);
            System.out.printf("Input heights = %s%n", arrayToString(tc.heights));
            System.out.printf("Expected: %d%n", tc.expected);
            System.out.printf("Actual:   %d%n", result);
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
        for (int i = 0; i < Math.min(arr.length, 20); i++) {  // Limit display to 20 elements
            sb.append(arr[i]);
            if (i < Math.min(arr.length, 20) - 1) sb.append(", ");
        }
        if (arr.length > 20) {
            sb.append("...");
        }
        sb.append("]");
        return sb.toString();
    }
}

