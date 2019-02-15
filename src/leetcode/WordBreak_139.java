package leetcode;

import java.util.List;

// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
// determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
// You may assume the dictionary does not contain duplicate words.
//
// For example, given
// s = "leetcode",
// dict = ["leet", "code"].
//
// Return true because "leetcode" can be segmented as "leet code".
public class WordBreak_139 {

  // dfs solution, TLE in leetcode
  // O(2^n) time
  public boolean wordBreak(String s, List<String> wordDict) {

    if (s.isEmpty() || wordDict.contains(s)) {
      return true;
    }

    for (int i = 1; i < s.length(); i++) {
      String substr = s.substring(0, i);

      if (wordDict.contains(substr) && wordBreak(s.substring(i), wordDict)) {
        return true;
      }
    }

    return false;
  }

  // dp[i] indicate if substring [0...i] can be split to words in dict
  // dp[i] == true if there is a dp[j] == true, 0 <= j < i and s.substring(j, i) in dict
  // O(n) space, O(n^2) time
  public boolean wordBreakDP(String s, List<String> wordDict) {

    int n = s.length();
    boolean[] dp = new boolean[n + 1];

    dp[0] = true;

    for (int i = 1; i <= n; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (dp[j] && wordDict.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }
}
