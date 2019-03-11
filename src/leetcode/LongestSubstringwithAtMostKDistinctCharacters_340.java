package leetcode;

import java.util.HashMap;

// Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
//
// For example, Given s = “eceba”,
//
// T is "ece" which its length is 3.
public class LongestSubstringwithAtMostKDistinctCharacters_340 {

  public int lengthOfLongestSubstringKDistinct(String s, int k) {

    final HashMap<Character, Integer> map = new HashMap<>();
    int begin = 0;
    int end = 0;
    int maxLength = 0;
    int counter = 0;

    while (end < s.length()) {

      char c = s.charAt(end);

      // must use getOrDefault here, because the frequency can be decreased.
      // use map.size() to indicate there are at most k distinct chars
      map.put(c, map.getOrDefault(c, 0) + 1);
      if (map.size() > k) {
        counter++;
      }

      while (counter > 0) {
        // --map[s[begin++]] == 0
        char first = s.charAt(begin++);
        map.put(first, map.get(first) - 1);

        if (map.get(first) == 0) {
          // removed a distinct character
          counter--;
          map.remove(first);
        }
      }

      maxLength = Math.max(maxLength, end - begin + 1);
      end++;
    }

    return maxLength;
  }
}
