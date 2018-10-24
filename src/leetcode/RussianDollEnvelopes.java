package leetcode;

import java.util.Arrays;

// You have a number of envelopes with widths and heights given as a pair of integers (w, h).
// One envelope can fit into another if and only if both the width and height of one envelope is greater
// than the width and height of the other envelope.
//
// What is the maximum number of envelopes can you Russian doll? (put one inside other)
//
// Example:
// Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
// the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
public class RussianDollEnvelopes {

  // DP + sort + binary search
  public int maxEnvelopes(int[][] envelopes) {

    if (envelopes.length == 0 || envelopes[0].length == 0) {
      return 0;
    }

    // sort the envelopes by width, then height
    Arrays.sort(envelopes, (x, y) -> {
      if (x[0] == y[0]) {
        return x[1] - y[1];
      }

      return x[0] - y[0];
    });

    int m = envelopes.length;
    int[] dp = new int[m];

    // at least one envelop
    Arrays.fill(dp, 1);

    for (int i = 1; i < m; i++) {
      int maxEnvelopes = 1;

      // linear search first, then change to binary search
      for (int j = i - 1; j >= 0; j--) {
        if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]
            && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
        }
      }
    }

    return Arrays.stream(dp).max().getAsInt();

  }
}
