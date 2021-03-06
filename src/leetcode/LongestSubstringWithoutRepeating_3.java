package leetcode;

import java.util.HashMap;
import java.util.Map;

/////
// Given a string, find the length of the longest substring without repeating characters.
//
// Examples:
// Given "abcabcbb", the answer is "abc", which the length is 3.
// Given "bbbbb", the answer is "b", with the length of 1.
// Given "pwwkew", the answer is "wke", with the length of 3.
//
// Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
///
public class LongestSubstringWithoutRepeating_3 {

  public int lengthOfLongestSubstring(String s) {

    Map<Character, Integer> map = new HashMap<>();
    int begin = 0, end = 0;
    int counter = 0;

    int maxLength = 0;

    while (end < s.length()) {
      char c = s.charAt(end);

      map.put(c, map.getOrDefault(c, 0) + 1);

      if (map.get(c) > 1) {
        // find a duplicate
        counter++;
      }

      while (counter > 0) {
        char first = s.charAt(begin);
        begin++;

        map.put(first, map.get(first) - 1);

        if (map.get(first) == 1) {
          counter--;
        }
      }

      // find a valid substring
      maxLength = Math.max(maxLength, end - begin + 1);
      end++;

      // if (!map.containsKey(c) || map.get(c) == 0) {
      //   map.put(c, 1);
      // } else {
      //   map.put(c, map.get(c) + 1);
      //   counter++;
      // }
      // end++;
      //
      // while (counter > 0) {
      //
      //   if (map.get(s.charAt(begin)) > 1) {
      //     counter--;
      //   }
      //
      //   map.put(s.charAt(begin), map.get(s.charAt(begin)) - 1);
      //   begin++;
      // }
      //
      // maxLength = Math.max(maxLength, end - begin);
    }
    return maxLength;
  }
}
