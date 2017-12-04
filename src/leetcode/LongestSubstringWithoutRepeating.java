package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * <p>
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0;
        int counter = 0;

        int maxLength = 0;

        while (end < s.length()) {
            char c = s.charAt(end);

            if (!map.containsKey(c) || map.get(c) == 0) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
                counter++;
            }
            end++;

            while (counter > 0) {

                if (map.get(s.charAt(begin)) > 1) {
                    counter--;
                }

                map.put(s.charAt(begin), map.get(s.charAt(begin)) - 1);
                begin++;
            }

            maxLength = Math.max(maxLength, end - begin);
        }
        return maxLength;
    }
}
