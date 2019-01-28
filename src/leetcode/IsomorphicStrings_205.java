package leetcode;

import java.util.*;

// Given two strings s and t, determine if they are isomorphic.
//
// Two strings are isomorphic if the characters in s can be replaced to get t.
//
// All occurrences of a character must be replaced with another character while preserving the order of characters.
// No two characters may map to the same character but a character may map to itself.
//
// Example 1:
//
// Input: s = "egg", t = "add"
// Output: true
// Example 2:
//
// Input: s = "foo", t = "bar"
// Output: false
// Example 3:
//
// Input: s = "paper", t = "title"
// Output: true
// Note:
// You may assume both s and t have the same length.
public class IsomorphicStrings_205 {

  // use hashmap to save the mapping relation
  // note: a -> b, indicate no others can map to "b",
  // also, a -> a, means no others can map to "a"
  // so use a hashset to track used characters in 't'.
  public boolean isIsomorphic(String s, String t) {
    HashMap<Character, Character> map = new HashMap<>();
    HashSet<Character> used = new HashSet<>();

    if (s.length() != t.length()) {
      return false;
    }

    int n = s.length();

    for (int i = 0; i < n; i++) {
      char c1 = s.charAt(i);
      char c2 = t.charAt(i);

      if (map.get(c1) != null && map.get(c1) != c2) {
        return false;
      }

      if (map.get(c1) == null && used.contains(c2)) {
        return false;
      }

      map.put(c1, c2);
      used.add(c2);

    }

    return true;
  }
}
