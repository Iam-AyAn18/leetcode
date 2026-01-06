/*
 * Longest Substring Without Repeating Characters
 * Medium
 *
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */

package org.neet.code.Medium.SlidingWindow;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {

        char[] str = s.toCharArray();
        int start=0;
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i =0; i<s.length();i++)
        {
            
        }
        return 0;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println("Example1 (expected 3): " + sol.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("Example2 (expected 1): " + sol.lengthOfLongestSubstring("bbbbb"));
        System.out.println("Example3 (expected 3): " + sol.lengthOfLongestSubstring("pwwkew"));
    }
}
