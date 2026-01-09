package org.neet.code.practice;

/**
 * 509. Fibonacci Number (LeetCode-style)
 * Easy
 * <p>
 * Given n, return the nth Fibonacci number.
 * The Fibonacci sequence is defined as:
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2) for n > 1
 * <p>
 * Example 1:
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1
 * <p>
 * Example 2:
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2
 * <p>
 * Example 3:
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * Constraints:
 * 0 <= n <= 30
 * <p>
 * Notes:
 * - Implementations may use recursion, iteration, dynamic programming, or matrix exponentiation.
 * - Do not include the solution implementation in this file; provide a method stub below.
 */
public class Fibonacci {

    /**
     * Returns the nth Fibonacci number.
     * This stub intentionally returns 0 so callers can run tests without an exception.
     * Replace with a real implementation when solving the problem.
     *
     * @param n the index (0-based) of the Fibonacci sequence
     * @return the nth Fibonacci number (stub returns 0)
     */
    public int fib2(int n) {

        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public int fib(int n) {
        if (n > 0) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            if (n >= 2) {
                for (int i = 2; i <= n; i++) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
            }
            return dp[n];
        } else
            return 0;
    }

    // Test harness for local verification: prints input, expected, actual and PASS/FAIL.
    public static void main(String[] args) {
        Fibonacci solver = new Fibonacci();

        class TestCase {
            final int n;
            final int expected;
            final String desc;

            TestCase(int n, int expected, String desc) {
                this.n = n;
                this.expected = expected;
                this.desc = desc;
            }
        }

        TestCase[] tests = new TestCase[]{
                new TestCase(0, 0, "base case n=0"),
                new TestCase(1, 1, "base case n=1"),
                new TestCase(2, 1, "example n=2"),
                new TestCase(3, 2, "example n=3"),
                new TestCase(4, 3, "example n=4"),
                new TestCase(10, 55, "n=10"),
                new TestCase(30, 832040, "n=30 upper constraint"),
        };

        for (int i = 0; i < tests.length; i++) {
            TestCase t = tests[i];
            System.out.println("Test #" + (i + 1) + " - " + t.desc);
            System.out.println("Input n = " + t.n);
            System.out.println("Expected: " + t.expected);

            int actual = solver.fib(t.n);
            System.out.println("Actual:   " + actual);
            if (actual == t.expected) {
                System.out.println("Result:   PASS\n");
            } else {
                System.out.println("Result:   FAIL\n");
            }

            if (actual == 0 && t.expected != 0) {
                System.out.println("Warning: actual returned 0 while expected is non-zero.\n");
            }
        }
    }
}
