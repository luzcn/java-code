package careercup.FB;

import java.util.*;

// Given a string and a set of characters
// find the shortest substring that used all the chars from given set
// Similar to Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/description/
public class ShortestSubstringFromGivenList {

  public int shortestSubstring(String s, List<Character> dict) {

    // two pointer + hash map
    HashMap<Character, Integer> map = new HashMap<>();
    for (char c : dict) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    int count = dict.size();

    int begin = 0;
    int end = 0;
    int minLength = Integer.MAX_VALUE;

    while (end < s.length()) {
      char c = s.charAt(end);

      map.put(c, map.getOrDefault(c, 0) - 1);
      if (map.get(c) >= 0) {
        count--;
      }

      while (count == 0) {
        // find a valid slide window
        minLength = Math.min(minLength, end - begin + 1);

        char first = s.charAt(begin++);
        map.put(first, map.get(first) + 1);

        if (map.get(first) > 0) {
          count++;
        }
      }

      end++;
    }

    return minLength;

  }
}
