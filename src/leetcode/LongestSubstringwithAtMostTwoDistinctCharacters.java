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
            if (!map.containsKey(c) || map.get(c) == 0) {
                map.put(c, 1);
                counter++;
            } else {
                map.put(c, map.get(c) + 1);
            }
            end++;

            while (counter > k) {

                if (map.get(s.charAt(begin)) == 1) {
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
