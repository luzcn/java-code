package leetcode;

import java.util.*;

// Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
//
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
// The matching should cover the entire input string (not partial).
//
// Note:
//
// s could be empty and contains only lowercase letters a-z.
// p could be empty and contains only lowercase letters a-z, and characters like . or *.
public class RegularExpressionMatching {

    // dfs solution
    public boolean isMatch(String s, String p) {

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean firstMath = (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));

        if (p.length() > 1 && p.charAt(1) == '*') {
            return (firstMath && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
        } else {
            return firstMath && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatchDP(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[m][n] = true;

        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                boolean first_match = i < m && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');

                // we must to put firstMath first,
                // otherwise, it may lead to index out of boundary error
                // in first match we already check i < m
                if (j + 1 < n && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

}
