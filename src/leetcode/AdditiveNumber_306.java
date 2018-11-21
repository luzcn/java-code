package leetcode;

// Additive number is a string whose digits can form additive sequence.
//
// A valid additive sequence should contain at least three numbers.
// Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
//
// Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
//
// Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
//
// Example 1:
// Input: "112358"
// Output: true
// Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
//              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
//
// Example 2:
// Input: "199100199"
// Output: true
// Explanation: The additive sequence is: 1, 99, 100, 199.
//              1 + 99 = 100, 99 + 100 = 199
// Follow up:
// How would you handle overflow for very large input integers?

public class AdditiveNumber_306 {

  public boolean isAdditiveNumber(String num) {

    return dfs(num, 0, null, null, false);
  }

  private boolean dfs(String num, int index, String num1, String num2, boolean isValid) {

    if (index >= num.length()) {
      return isValid;
    }

    for (int i = index; i < num.length(); i++) {

      String value = num.substring(index, i + 1);

      if (value.length() > 1 && value.charAt(0) == '0') {
        continue;
      }

      if (num1 == null) {
        if (dfs(num, i + 1, value, num2, false)) {
          return true;
        }
      } else if (num2 == null) {
        if (dfs(num, i + 1, num1, value, false)) {
          return true;
        }
      } else {

        if (Long.parseLong(num1) + Long.parseLong(num2) == Long.parseLong(value)) {
          if (dfs(num, i + 1, num2, value, true)) {
            return true;
          }
        }
      }
    }

    return false;
  }

}
