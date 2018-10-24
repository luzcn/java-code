package leetcode;

// Created by zhenlu on 9/16/16.

import java.util.HashMap;
import java.util.Map;

/////
// A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number of ways to decode it.
//
// For example,
// Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
//
// The number of ways decoding "12" is 2.
///
public class DecodeWays {

  private int dfs(String s, Map<String, Integer> map) {
    if (map.containsKey(s)) {
      return map.get(s);
    }

    if (s.isEmpty()) {
      return 1;
    }

    char c = s.charAt(0);
    int ways = 0;
    if (c != '0') {
      ways += dfs(s.substring(1), map);
    }

    if (s.length() > 1) {
      if (c == '1' || (c == '2' && Character.getNumericValue(s.charAt(1)) <= 6)) {
        ways += dfs(s.substring(2), map);
      }
    }
    map.put(s, ways);
    return ways;
  }


  public int decodeWaysDP(String s) {
    int n = s.length();

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= s.length(); i++) {
      char current = s.charAt(i - 1);
      if (current != '0') {
        dp[i] = dp[i - 1];
      }

      char prev = s.charAt(i - 2);

      if (prev == '1' || (prev == '2' && current <= '6' && current >= '0')) {
        dp[i] += dp[i - 2];

      }
    }

    return dp[n];
  }

  public int numDecodings(String s) {

    if (s.isEmpty() || s.startsWith("0")) {
      return 0;
    }

    return dfs(s, new HashMap<>());
  }
}
