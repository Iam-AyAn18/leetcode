/*
 * Largest Even Number From String
 * Contest - Easy
 *
 * Problem statement:
 * Given a string s consisting of digits ('0' - '9'), remove zero or more characters from s (without changing the
 * relative order of the remaining characters) to form the largest possible even integer (in decimal) and return it
 * as a string. The resulting number must not contain leading zeros unless the number is exactly "0".
 * If it is impossible to obtain an even integer, return an empty string.
 *
 * Notes:
 * - You may remove any number of digits (including zero) but must keep the remaining digits in their original order.
 * - The goal is to maximize the numeric value of the resulting even integer.
 *
 * Examples:
 * Example 1:
 * Input: s = "52"
 * Output: "52"
 * Explanation: The entire string is already an even number and is the largest possible.
 *
 * Example 2:
 * Input: s = "1234"
 * Output: "1234"
 * Explanation: "1234" is even and is larger than any other even subsequence.
 *
 * Example 3:
 * Input: s = "1357"
 * Output: ""
 * Explanation: There is no even digit in the string, so it's impossible to form an even number.
 *
 * Example 4:
 * Input: s = "1002"
 * Output: "1002"
 * Explanation: "1002" is even. Note that leading zeros are not allowed unless the number is exactly "0".
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists only of digits '0' through '9'.
 *
 * Problem requirements (LeetCode-style):
 * - Implement a function `public String largestEvenNumber(String s)` that returns the largest possible even number
 *   (as a string) obtainable by deleting characters from s without reordering.
 * - Do not print or log the solution from the method. Unit tests or main may call the method to check behavior.
 *
 * Implementation note:
 * - This file is a contest-style stub. The implementation is intentionally left as a TODO; do not include a full
 *   solution here if the request is to only add the problem statement.
 */
package org.neet.code.Contest.Easy;

public class LargestEvenNumberFromString {

    /**
     * Returns the largest even number (as a string) obtainable by deleting characters from s (keeping order).
     * If it's impossible to form an even number, return an empty string.
     */
    public String largestEvenNumber(String s) {
        if (s == null || s.length() == 0) return "";

        // Find last even digit
        int lastEven = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int d = c - '0';
            if ((d & 1) == 0) { // even
                lastEven = i;
                break;
            }
        }
        if (lastEven == -1) return ""; // no even digit

        String prefix = s.substring(0, lastEven + 1);
        // Strip leading zeros
        int idx = 0;
        while (idx < prefix.length() && prefix.charAt(idx) == '0') idx++;
        if (idx == prefix.length()) {
            // all zeros -> single "0"
            return "0";
        }
        if (idx > 0) return prefix.substring(idx);
        return prefix;
    }

    // Example test harness (no assertions) to show expected outputs for the problem statement.
    public static void main(String[] args) {
        LargestEvenNumberFromString sol = new LargestEvenNumberFromString();

        String s1 = "52";
        System.out.println("Input: \"52\" -> Expected: \"52\" -> Got: " + sol.largestEvenNumber(s1));

        String s2 = "1234";
        System.out.println("Input: \"1234\" -> Expected: \"1234\" -> Got: " + sol.largestEvenNumber(s2));

        String s3 = "1357";
        System.out.println("Input: \"1357\" -> Expected: \"\" -> Got: " + sol.largestEvenNumber(s3));

        String s4 = "2211112112";
        System.out.println("Input: \"2211112112\" -> Expected: \"2211112112\" -> Got: " + sol.largestEvenNumber(s4));

        // Additional tests
        System.out.println("Input: \"0002\" -> Expected: \"2\" -> Got: " + sol.largestEvenNumber("0002"));
        System.out.println("Input: \"0\" -> Expected: \"0\" -> Got: " + sol.largestEvenNumber("0"));
        System.out.println("Input: \"00\" -> Expected: \"0\" -> Got: " + sol.largestEvenNumber("00"));
        System.out.println("Input: \"7\" -> Expected: \"\" -> Got: " + sol.largestEvenNumber("7"));
        System.out.println("Input: \"10\" -> Expected: \"10\" -> Got: " + sol.largestEvenNumber("10"));
    }
}
