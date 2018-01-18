package leetcode;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 *
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 *
 * Note:
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */
public class DecodeWays2 {
    private static final int M = 1000000007;

    public int numDecodings(String s) {

        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : 1;

        for (int i = 2; i <= n; i++) {

            char current = s.charAt(i - 1);
            char prev = s.charAt(i - 2);

            if (current == '*') {
                dp[i] = dp[i - 1] * 9;

                if (prev == '1') {
                    // dp[i] += dp[i - 2] * 9;
                    dp[i] = (dp[i] + dp[i - 2] * 9) % M;
                } else if (prev == '2') {
                    // dp[i] += dp[i - 2] * 6;
                    dp[i] = (dp[i] + dp[i - 2] * 6) % M;
                } else if (prev == '*') {
                    // dp[i] += 15 * dp[i-2];
                    dp[i] = (dp[i] + 15 * dp[i - 2]) % M;
                }
            } else {
                dp[i] = current == '0' ? 0 : dp[i - 1];

                if (prev == '1' || (prev == '2' && current <= '6')) {
                    // dp[i] += dp[i - 2];
                    dp[i] = (dp[i] + dp[i - 2]) % M;
                } else if (prev == '*') {
                    // dp[i] += (current <= '6' ? 2 : 1) * dp[i - 2];
                    dp[i] = (dp[i] + (current <= '6' ? 2 : 1) * dp[i - 2]) % M;
                }
            }

        }

        return (int) dp[n];

    }
}
