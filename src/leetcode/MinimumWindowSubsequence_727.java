package leetcode;

// Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
//
// If there is no such window in S that covers all characters in T, return the empty string "".
// If there are multiple such minimum-length windows, return the one with the left-most starting index.
//
// Example 1:
//
// Input:
// S = "abcdebdde", T = "bde"
// Output: "bcde"
// Explanation:
// "bcde" is the answer because it occurs before "bdde" which has the same length.
// "deb" is not a smaller window because the elements of T in the window must occur in order.
public class MinimumWindowSubsequence_727 {

  public String minWindow(String S, String T) {

    return minWindowBruteForce(S, T);

  }


  private String minWindowDP(String s, String t) {
    return null;
  }

  // O(n^2 * m) n is s.length, m is t.length
  private String minWindowBruteForce(String s, String t) {

    int minLength = Integer.MAX_VALUE;
    int index = 0;

    for (int i = 0; i <= s.length() - t.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        if (isSubSequence(s.substring(i, j), t)) {
          if (j - i < minLength) {
            index = i;
            minLength = j - i;
          }
        }
      }
    }

    return s.substring(index, index + minLength);
  }

  private boolean isSubSequence(String s, String t) {
    int i = 0;
    int j = 0;
    while (i < s.length() && j < t.length()) {

      if (s.charAt(i) == t.charAt(j)) {
        i++;
        j++;
      } else {
        i++;
      }
    }

    return j == t.length();
  }
}
