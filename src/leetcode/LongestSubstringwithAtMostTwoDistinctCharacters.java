package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * <p>
 * For example, Given s = “eceba”,
 * <p>
 * T is "ece" which its length is 3.
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0;
        int maxLength = 0;
        int counter = 0;

        while (end < s.length()) {

            char c = s.charAt(end);
            if (map.getOrDefault(c, 0) == 0) {
                counter++;
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }

            while (counter > k) {
                // --map[s[begin++]] == 0
                char first = s.charAt(begin++);
                map.put(first, map.get(first) - 1);

                if (map.get(first) == 0) {
                    counter--;
                }
            }

            maxLength = Math.max(maxLength, end - begin + 1);
            end++;
        }

        return maxLength;
    }
}
