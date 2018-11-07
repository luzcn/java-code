package leetcode;

import java.util.HashMap;

// Given a string, determine if a permutation of the string could form a palindrome.
//
// Example 1:
//
// Input: "code" Output: false Example 2:
//
// Input: "aab" Output: true Example 3:
//
// Input: "carerac" Output: true
public class PalindromePermutation_266 {

  HashMap<Character, Integer> map = new HashMap<>();

  public boolean canPermutePalindrome(String s) {
    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int count = 0;

    for (int frequency : map.values()) {
      if (frequency % 2 != 0 && ++count > 1) {
        return false;
      }
    }
    return true;
  }

}
