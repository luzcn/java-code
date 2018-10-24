package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
// In Pascal's triangle, each number is the sum of the two numbers directly above it.
//
// Example:
//
// Input: 5
// Output:
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]
public class PascalTriangle {

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();

    for (int level = 0; level < numRows; level++) {
      triangle.add(new ArrayList<>(Collections.nCopies(level + 1, 1)));

      for (int i = 1; i < level; i++) {
        triangle.get(level)
            .set(i, triangle.get(level - 1).get(i - 1) + triangle.get(level - 1).get(i));
      }
    }

    return triangle;
  }

  // 119. Pascal's Triangle II
  // Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
  public List<Integer> getRow(int rowIndex) {
    List<Integer> res = new ArrayList<>();
    int[] row = new int[rowIndex + 1];
    row[0] = 1;

    for (int level = 1; level <= rowIndex; level++) {

      for (int i = level; i > 0; i--) {
        row[i] = row[i] + row[i - 1];
      }
    }

    for (int n : row) {
      res.add(n);
    }

    return res;

  }
}
