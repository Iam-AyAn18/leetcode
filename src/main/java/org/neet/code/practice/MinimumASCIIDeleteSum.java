package org.neet.code.practice;

/**
 * 712. Minimum ASCII Delete Sum for Two Strings (LeetCode-style)
 * Medium
 *
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 *
 * This file contains a solution implementation and a small main() with test cases.
 */
public class MinimumASCIIDeleteSum {

    /**
     * Dynamic programming solution.
     * dp[i][j] = minimum ASCII delete sum to make s1[i:] and s2[j:] equal.
     * Time: O(m*n), Space: O(m*n) where m = s1.length(), n = s2.length().
     */
    public int minimumDeleteSum(String s1, String s2) {
        if (s1 == null) s1 = "";
        if (s2 == null) s2 = "";
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // base cases: when one string is empty, cost is sum of ASCII of the other
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + s1.charAt(i);
        }
        for (int j = n - 1; j >= 0; j--) {
            dp[m][j] = dp[m][j + 1] + s2.charAt(j);
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(
                        s1.charAt(i) + dp[i + 1][j],
                        s2.charAt(j) + dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        MinimumASCIIDeleteSum solver = new MinimumASCIIDeleteSum();

        class TestCase {
            final String s1;
            final String s2;
            final int expected;
            final String desc;
            TestCase(String s1, String s2, int expected, String desc) {
                this.s1 = s1; this.s2 = s2; this.expected = expected; this.desc = desc;
            }
        }

        TestCase[] tests = new TestCase[] {
            new TestCase("sea", "eat", 231, "example1"),
            new TestCase("delete", "leet", 403, "example2"),
            new TestCase("", "abc", 'a' + 'b' + 'c', "empty s1"),
            new TestCase("abc", "", 'a' + 'b' + 'c', "empty s2"),
            new TestCase("abc", "abc", 0, "identical strings"),
            new TestCase("axc", "abc", 'x' + 'b', "single mismatch middle"),
            new TestCase("a", "b", 'a' + 'b', "single characters different"),
            new TestCase("cba", "abc", 390, "reverse strings"),
        };

        for (int i = 0; i < tests.length; i++) {
            TestCase t = tests[i];
            System.out.println("Test #" + (i+1) + " - " + t.desc);
            System.out.println("s1 = \"" + t.s1 + "\", s2 = \"" + t.s2 + "\"");
            System.out.println("Expected: " + t.expected);
            int actual = solver.minimumDeleteSum(t.s1, t.s2);
            System.out.println("Actual:   " + actual);
            System.out.println(actual == t.expected ? "Result:   PASS" : "Result:   FAIL");
            System.out.println();
        }
    }
}
