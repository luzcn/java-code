package leetcode;

import java.util.ArrayList;
import java.util.List;

// leetcode 131
// Given a string s, partition s such that every substring of the partition is a palindrome.
//
// Return all possible palindrome partitioning of s.
//
// Example:
//
// Input: "aab"
// Output:
// [
//   ["aa","b"],
//   ["a","a","b"]
// ]

public class PalindromePartitioning_131_132 {

  // dfs + backtracking
  private List<List<String>> res = new ArrayList<>();

  private void dfs(String s, List<String> current) {
    if (s.isEmpty()) {
      res.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < s.length(); i++) {
      String prefix = s.substring(0, i + 1);

      if (isValid(prefix)) {
        current.add(prefix);

        dfs(s.substring(i + 1), current);

        current.remove(current.size() - 1);
      }
    }
  }


  private boolean isValid(String s) {
    int i = 0;
    int j = s.length() - 1;

    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }

  public List<List<String>> partition(String s) {
    dfs(s, new ArrayList<>());

    return res;
  }

  // 132. Palindrome Partitioning II
  // Given a string s, partition s such that every substring of the partition is a palindrome.
  //
  // Return the minimum cuts needed for a palindrome partitioning of s.
  //
  // Example:
  //
  // Input: "aab"
  // Output: 1
  // Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

  public int minCut(String s) {
    // dp idea

    int n = s.length();
    if (n <= 1) {
      return 0;
    }

    int[] dp = new int[n];
    boolean[][] isValid = new boolean[n][n];
    for (int i = 1; i < n; i++) {
      dp[i] = i;
    }

    for (int i = 1; i < n; i++) {
      for (int j = i; j >= 0; j--) {
        if (s.charAt(i) == s.charAt(j) && (i == j || j + 1 == i || isValid[j + 1][i - 1])) {

          isValid[j][i] = true;
          dp[i] = Math.min(dp[i], dp[j] + 1);
        }
      }
    }

    return dp[n - 1];
  }
}
