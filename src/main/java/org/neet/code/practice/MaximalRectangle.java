package org.neet.code.practice;

/**
 * 85. Maximal Rectangle (LeetCode-style)
 * Hard
 *
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * This file contains an implementation (histogram + monotonic stack) and a small main() with tests.
 */
public class MaximalRectangle {

    /**
     * Compute maximal rectangle area in a binary matrix using histogram approach.
     * Time: O(rows * cols), Space: O(cols)
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int max = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') heights[c] += 1;
                else heights[c] = 0;
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }

    // Largest rectangle in histogram (monotonic stack)
    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
        int max = 0;
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }

    // Helper to build char[][] from an array of strings where each string is a row like "10101"
    private static char[][] build(String[] rows) {
        if (rows == null || rows.length == 0) return new char[0][0];
        int R = rows.length;
        int C = rows[0].length();
        char[][] mat = new char[R][C];
        for (int i = 0; i < R; i++) {
            mat[i] = rows[i].toCharArray();
        }
        return mat;
    }

    public static void main(String[] args) {
        MaximalRectangle solver = new MaximalRectangle();

        class TestCase {
            final String[] rows;
            final int expected;
            final String desc;
            TestCase(String[] rows, int expected, String desc) { this.rows = rows; this.expected = expected; this.desc = desc; }
        }

        TestCase[] tests = new TestCase[] {
            new TestCase(new String[] {"10100","10111","11111","10010"}, 6, "example1"),
            new TestCase(new String[] {"0"}, 0, "example2 single 0"),
            new TestCase(new String[] {"1"}, 1, "example3 single 1"),
            new TestCase(new String[] {"00","00"}, 0, "all zeros"),
            new TestCase(new String[] {"11","11"}, 4, "all ones 2x2"),
            new TestCase(new String[] {"1011","1111","1110"}, 6, "mixed matrix"),
            new TestCase(new String[] {"1","1","1","1"}, 1, "single-column ones"),
            new TestCase(new String[] {"1111"}, 4, "single-row ones"),
        };

        for (int i = 0; i < tests.length; i++) {
            TestCase t = tests[i];
            System.out.println("Test #" + (i+1) + " - " + t.desc);
            System.out.println("Input:");
            for (String row : t.rows) System.out.println(row);
            int actual = solver.maximalRectangle(build(t.rows));
            System.out.println("Expected: " + t.expected + ", Actual: " + actual + (actual == t.expected ? " -> PASS" : " -> FAIL"));
            System.out.println();
        }
    }
}

