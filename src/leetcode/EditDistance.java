package leetcode;

import java.util.HashMap;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * 1. Insert a character
 * 2. Delete a character
 * 3. Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class EditDistance {

    //     The idea is: ed("", s) || ed(s,"") = s.size();
    //     The strings can be considered as ch1 + s1 and ch2 + s2
//
//     so ed(ch1 + s1, ch2 + s2) =
//     - if ch1==ch2, ed(s1, s2)
//     - otherwise, we have 3 choice
//     1. insert ch1 to s2, then = ed(s1, ch2 + s2)
//     2. delete ch2 from s2, then = ed(ch1 + s1, s2);
//     3. replace, then = ed(s1, s2)
    private int dfs(String s, String t) {
        if (s.isEmpty()) {
            return t.length();
        }

        if (t.isEmpty()) {
            return s.length();
        }

        if (map.containsKey(s + "#" + t)) {
            return map.get(s + "#" + t);
        }

        int distance = 0;
        if (s.charAt(0) == t.charAt(0)) {
            distance = dfs(s.substring(1), t.substring(1));
        } else {
            distance = Math.min(Math.min(1 + dfs(s.substring(1), t), 1 + dfs(s, t.substring(1))),
                    1 + dfs(s.substring(1), t.substring(1)));
        }

        map.put(s + "#" + t, distance);

        return distance;
    }

    // recursive with memoization
    private HashMap<String, Integer> map = new HashMap<>();

private int minDistanceDP(String s, String t) {
    int m = s.length();
    int n = t.length();

    int[][] dp = new int[m + 1][n + 1];

    // s is empty, the distance is t.length
    for (int i = 0; i <= m; i++) {
        dp[i][0] = i;
    }

    // t is empty, the distance is s.length
    for (int j = 0; j <= n; j++) {
        dp[0][j] = j;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
            }
        }
    }
    return dp[m][n];
}

    public int minDistance(String word1, String word2) {
        return minDistanceDP(word1, word2);
    }
}
