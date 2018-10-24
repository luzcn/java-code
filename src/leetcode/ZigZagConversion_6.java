package leetcode;

import java.util.ArrayList;
import java.util.List;

// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
// (you may want to display this pattern in a fixed font for better legibility)
//
// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"
//
// Write the code that will take a string and make this conversion given a number of rows:
//
// string convert(string s, int numRows);
// Example 1:
//
// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"
public class ZigZagConversion_6 {

  public String convert(String s, int numRows) {

    // sort by row
    // use min(s.length(), numsRows) lists to represent the non-empty rows of the Zig-Zag Pattern
    // iterate through s from left to right, appending each character to the appropriate row.
    // - The appropriate row can be tracked using two variables: the current row and the current direction.
    // The current direction changes only when we moved up to the topmost row or moved down to the bottommost row.
    if (numRows == 1) {
      return s;
    }

    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < Math.min(s.length(), numRows); i++) {
      rows.add(new StringBuilder());
    }
    int currentRow = 0;
    boolean goingDown = false;

    for (char c : s.toCharArray()) {
      rows.get(currentRow).append(c);

      if (currentRow == 0 || currentRow == numRows - 1) {
        goingDown = !goingDown;

      }
      if (goingDown) {
        currentRow++;
      } else {
        currentRow--;
      }
    }

    StringBuilder res = new StringBuilder();
    for (StringBuilder row : rows) {
      res.append(row);
    }

    return res.toString();
  }
}
