package leetcode;

import java.util.ArrayList;
import java.util.List;

// Palindrome Permutation II
// Given a string s, return all the palindromic permutations (without duplicates) of it.
// Return an empty list if no palindromic permutation could be form.
//
// Example 1:
//
// Input: "aabb"
// Output: ["abba", "baab"]
// Example 2:
//
// Input: "abc"
// Output: []


public class PalindromePermutation2_267 {


  // recursive + backtrack
  List<String> result = new ArrayList<>();
  int[] chars = new int[256];

  public List<String> generatePalindromes(String s) {
    int n = s.length();

    for (char c : s.toCharArray()) {
      chars[c]++;
    }

    int count = 0;
    Character c = null;
    for (int i = 0; i < 256; i++) {
      if (chars[i] % 2 != 0) {
        if (++count > 1) {
          return result;
        } else {
          c = (char) i;
        }
      }
    }

    if (count == 1) {
      chars[c]--;
      dfs(c + "", n);
    } else {
      dfs("", n);
    }

    return result;
  }


  private void dfs(String current, int n) {
    if (current.length() == n) {
      result.add(current);
    }

    for (int i = 0; i < 256; i++) {
      if (chars[i] == 0) {
        continue;
      }

      char c = (char) i;

      chars[i] -= 2;

      dfs(c + current + c, n);

      chars[i] += 2;
    }
  }
}
