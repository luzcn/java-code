package leetcode;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings
 * even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * Note:
 * The input string length won't exceed 1000.
 */
public class PalindromicSubstrings {

    // actually, we don't need use DP.
    // there is no longest or shortest such optimization requirements
    // we can extend from the center, there are 2*n - 1 center positions
    // either a single character or the middle of two characters
    // this is also O(n^2) time
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        for (int center = 0; center <= 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }

        return count;
    }


    // dp solution, similar to longest palindrome
    // count how may dp[i][j] == 1
    public int countPalindromeSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();


        int count = 0;
        boolean[][] dp = new boolean[1000][1000];

        // since each single character is always palindromic, need to set as true
        // and there should be at least s.length palindromic substrings
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // check two continuous characters
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                dp[i][i + 1] = true;
            }
        }


        for (int i = n - 3; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
