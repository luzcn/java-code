package leetcode;

import java.util.HashSet;

// Given a picture consisting of black and white pixels, and a positive integer N,
// find the number of black pixels located at some specific row R and column C that align with all the following rules:
//
// - Row R and column C both contain exactly N black pixels.
// - For all rows that have a black pixel at column C, they should be exactly the same as row R
//
// The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
//
// Example:
// Input:
// [['W', 'B', 'W', 'B', 'B', 'W'],
//  ['W', 'B', 'W', 'B', 'B', 'W'],
//  ['W', 'B', 'W', 'B', 'B', 'W'],
//  ['W', 'W', 'B', 'W', 'B', 'W']]
//
// N = 3
// Output: 6
// Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
//         0    1    2    3    4    5         column index
// 0    [['W', 'B', 'W', 'B', 'B', 'W'],
// 1     ['W', 'B', 'W', 'B', 'B', 'W'],
// 2     ['W', 'B', 'W', 'B', 'B', 'W'],
// 3     ['W', 'W', 'B', 'W', 'B', 'W']]
// row index
//
// Take 'B' at row R = 0 and column C = 1 as an example:
// Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels.
// Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.
public class LonelyPixel2_533 {

  // similar to lonelyPixel_1, use DP idea, pre-calculate the 'B' number for each row and column
  // the difficult part is rule2, "they should be exactly the same as row R"
  // 1. when we find a column j, has N black pixel, we scan the row
  // - if we found picture[i][j] is 'B', but rows[i] != N, break
  // - otherwise, save the string representation of picture[i] into a hash set
  // 2. check if the hash set size is 1, if yes, there are N black pixel are lonely pixels.
  public int findLonelyPixel(char[][] picture, int N) {
    int m = picture.length;
    if (m == 0) {
      return 0;
    }
    int n = picture[0].length;
    int res = 0;

    int[] rows = new int[m];
    int[] cols = new int[n];

    HashSet<String> seen = new HashSet<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (picture[i][j] == 'B') {
          rows[i]++;
          cols[j]++;
        }
      }
    }

    for (int j = 0; j < n; j++) {
      if (cols[j] == N) {
        seen.clear();

        for (int i = 0; i < m; i++) {
          if (picture[i][j] == 'B') {
            if (rows[i] != N) {
              seen.clear();
              break;
            }

            seen.add(new String(picture[i]));
          }
        }

        if (seen.size() == 1) {
          res += N;
        }
      }
    }

    return res;
  }
}
