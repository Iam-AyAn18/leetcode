package org.neet.code.Easy.math;

/**
 * 1266. Minimum Time Visiting All Points
 * Easy
 *
 * On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi].
 * Return the minimum time in seconds to visit all the points in the order given by points.
 *
 * You can move according to these rules:
 * - In 1 second, you can either:
 *   - move vertically by one unit,
 *   - move horizontally by one unit, or
 *   - move diagonally sqrt(2) units (in other words, move one unit vertically then one unit horizontally in 1 second).
 * - You have to visit the points in the same order as they appear in the array.
 * - You are allowed to pass through points that appear later in the order, but these do not count as visits.
 *
 * Example 1:
 * Input: points = [[1,1],[3,4],[-1,0]]
 * Output: 7
 * Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
 * Time from [1,1] to [3,4] = 3 seconds
 * Time from [3,4] to [-1,0] = 4 seconds
 * Total time = 7 seconds
 *
 * Example 2:
 * Input: points = [[3,2],[-2,2]]
 * Output: 5
 *
 * Constraints:
 * points.length == n
 * 1 <= n <= 100
 * points[i].length == 2
 * -1000 <= points[i][0], points[i][1] <= 1000
 *
 * NOTE: This file is a LeetCode-style stub. The solution implementation is intentionally left as a TODO.
 */
public class MinimumTimeVisitingAllPoints {

    /**
     * Returns the minimum time in seconds to visit all points in order.
     * TODO: implement this method.
     *
     * @param points array of 2D coordinates
     * @return minimum time to visit all points
     */
    public int minTimeToVisitAllPoints(int[][] points) {

        int n = points.length;
        int x1 = points[0][0];
        int y1 = points[0][1];

        int sum = 0;

        for(int i =1; i< n; i++){
            int x2 = points[i][0];
            int y2 = points[i][1];

            sum+= Math.max(Math.abs(x2-x1), Math.abs(y2-y1));
            x1 = x2;
            y1 = y2;
        }
        return sum;
    }

    // Test case record for structured testing
    record TestCase(int[][] points, int expected, String description) {}

    public static void main(String[] args) {
        MinimumTimeVisitingAllPoints solution = new MinimumTimeVisitingAllPoints();

        // Define test cases
        TestCase[] testCases = {

                // Backtracking
                new TestCase(
                        new int[][]{{5, 5}, {0, 0}, {5, 5}},
                        10,
                        "Go and return to same point"
                ),
            // Example test cases from problem statement
            new TestCase(
                new int[][]{{1, 1}, {3, 4}, {-1, 0}},
                7,
                "Example 1 - multiple points with diagonal and straight moves"
            ),
            new TestCase(
                new int[][]{{3, 2}, {-2, 2}},
                5,
                "Example 2 - horizontal move only"
            ),

            // Edge cases
            new TestCase(
                new int[][]{{0, 0}},
                0,
                "Single point - no movement needed"
            ),
            new TestCase(
                new int[][]{{0, 0}, {0, 0}},
                0,
                "Two identical points - no movement needed"
            ),

            // Diagonal moves
            new TestCase(
                new int[][]{{0, 0}, {1, 1}},
                1,
                "Pure diagonal move"
            ),
            new TestCase(
                new int[][]{{0, 0}, {3, 3}},
                3,
                "Multiple diagonal moves"
            ),

            // Straight moves
            new TestCase(
                new int[][]{{0, 0}, {5, 0}},
                5,
                "Pure horizontal move"
            ),
            new TestCase(
                new int[][]{{0, 0}, {0, 5}},
                5,
                "Pure vertical move"
            ),

            // Mixed moves (diagonal + straight)
            new TestCase(
                new int[][]{{0, 0}, {2, 5}},
                5,
                "Diagonal then vertical: 2 diagonal + 3 vertical = 5"
            ),
            new TestCase(
                new int[][]{{0, 0}, {5, 2}},
                5,
                "Diagonal then horizontal: 2 diagonal + 3 horizontal = 5"
            ),

            // Negative coordinates
            new TestCase(
                new int[][]{{-5, -5}, {5, 5}},
                10,
                "Negative to positive - pure diagonal"
            ),
            new TestCase(
                new int[][]{{-3, 2}, {2, -3}},
                5,
                "Cross quadrants"
            ),

            // Multiple points
            new TestCase(
                new int[][]{{0, 0}, {1, 1}, {2, 2}},
                2,
                "Three points in diagonal line"
            ),
            new TestCase(
                new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}, {0, 0}},
                4,
                "Square path returning to start"
            ),

            // Large coordinates
            new TestCase(
                new int[][]{{-1000, -1000}, {1000, 1000}},
                2000,
                "Maximum range - corner to corner"
            ),
            new TestCase(
                new int[][]{{-1000, 0}, {1000, 0}},
                2000,
                "Maximum horizontal distance"
            ),

            // Zigzag pattern
            new TestCase(
                new int[][]{{0, 0}, {2, 0}, {2, 2}, {0, 2}},
                6,
                "Rectangle path"
            )

        };

        // Run all test cases
        System.out.println("Running Minimum Time Visiting All Points Tests...\n");
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int result = solution.minTimeToVisitAllPoints(tc.points);
            boolean isPass = result == tc.expected;

            if (isPass) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test #%d - %s%n", i + 1, tc.description);
            System.out.printf("Input points = %s%n", pointsToString(tc.points));
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

    private static String pointsToString(int[][] points) {
        if (points == null || points.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < points.length; i++) {
            sb.append("[").append(points[i][0]).append(",").append(points[i][1]).append("]");
            if (i < points.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}


