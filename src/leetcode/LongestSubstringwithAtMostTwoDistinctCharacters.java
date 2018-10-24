package leetcode;

import java.util.HashMap;

// Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
//
// For example, Given s = “eceba”,
//
// T is "ece" which its length is 3.
public class LongestSubstringwithAtMostTwoDistinctCharacters {

  public int lengthOfLongestSubstringTwoDistinct(String s, int k) {

    HashMap<Character, Integer> map = new HashMap<>();
    int begin = 0, end = 0;
    int maxLength = 0;
    int counter = 0;

    while (end < s.length()) {

      char c = s.charAt(end);

      // must use getOrDefault here, because the frequency can be decreased.
      if (map.getOrDefault(c, 0) == 0) {
        counter++;
        map.put(c, 1);
      } else {
        map.put(c, map.get(c) + 1);
      }

      while (counter > k) {
        // --map[s[begin++]] == 0
        char first = s.charAt(begin);

        if (map.get(first) == 1) {
          counter--;
        }

        map.put(first, map.get(first) - 1);
        begin++;

      }

      maxLength = Math.max(maxLength, end - begin + 1);
      end++;
    }

    return maxLength;
  }
}
