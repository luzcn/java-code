package leetcode;

// Given a string containing only three types of characters: '(', ')' and '*',
// write a function to check whether this string is valid. We define the validity of a string by these rules:
//
// - Any left parenthesis '(' must have a corresponding right parenthesis ')'.
// - Any right parenthesis ')' must have a corresponding left parenthesis '('.
// - Left parenthesis '(' must go before the corresponding right parenthesis ')'.
// - '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
// - An empty string is also valid.
//
// Example 1:
// Input: "()"
// Output: True
//
// Example 2:
// Input: "(*)"
// Output: True
//
// Example 3:
// Input: "(*))"
// Output: True
public class ValidParenthesisString_678 {

  public boolean checkValidString(String s) {
    return dfs(s, 0, 0);
  }

  // recursive
  // if s[i] is *, it has 3 choices, use "", (, or )
  private boolean dfs(String s, int count, int index) {
    if (count < 0) {
      return false;
    }

    if (index >= s.length()) {
      return count == 0;
    }

    if (s.charAt(index) == '(') {
      return dfs(s, count + 1, index + 1);
    }

    if (s.charAt(index) == ')') {
      return dfs(s, count - 1, index + 1);
    }

    return dfs(s, count + 1, index + 1) || dfs(s, count - 1, index + 1) || dfs(s, count, index + 1);
  }
}
