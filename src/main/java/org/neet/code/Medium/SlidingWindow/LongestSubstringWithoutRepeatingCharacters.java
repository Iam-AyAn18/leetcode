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

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] str = s.toCharArray();
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(str[i], map.getOrDefault(str[i], 0) + 1);
            while (map.get(str[i]) > 1) {
                map.put(str[start], map.get(str[start]) - 1);
                if (map.get(str[start]) == 0) {
                    map.remove(str[start]);
                }
                start++;
            }
            if (maxLen < map.size()) {
                maxLen = map.size();
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) {
        Queue<Character> q = new ArrayDeque<>();
        int maxLen =0;
        for(int i=0; i<s.length();i++)
        {
            if(!q.contains(s.charAt(i))){
                q.offer(s.charAt(i));
                if(maxLen<q.size())
                    maxLen = q.size();
            }
            else{
                while(q.contains(s.charAt(i)))
                    q.poll();
                q.offer(s.charAt(i));
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring3(String s) {
        HashSet<Character> set = new HashSet<>();
        int maxLen =0;
        int start =0;
        for(int i=0; i<s.length();i++)
        {
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                if(maxLen<set.size())
                    maxLen = set.size();
            }
            else{
                while(set.contains(s.charAt(i))) {
                    set.remove(s.charAt(start++));
                }
                set.add(s.charAt(i));
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println("Example1 (expected 3): " + sol.lengthOfLongestSubstring3("abcabcbb"));
        System.out.println("Example2 (expected 1): " + sol.lengthOfLongestSubstring3("bbbbb"));
        System.out.println("Example3 (expected 3): " + sol.lengthOfLongestSubstring3("pwwkew"));
    }
}
