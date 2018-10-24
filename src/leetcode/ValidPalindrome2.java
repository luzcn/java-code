package leetcode;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a
 * palindrome.
 *
 * Example 1: Input: "aba" Output: True
 *
 * Example 2: Input: "abca" Output: True Explanation: You could delete the character 'c'. Note: The
 * string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome2 {

  // this question can only delete once, so there are only 2 situation, l++ or r--
  // so, dfs is an overkill
  // we can scan the string twice, similar to the valid palindrome 1 problem.
  // if either iteration is true, return true.
  public boolean validPalindrome(String s) {

    s = s.trim();
    if (s.isEmpty()) {
      return true;
    }

    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      if (s.charAt(left) == s.charAt(right)) {
        left++;
        right--;
      } else {
        return isValid(s, left + 1, right) || isValid(s, left, right - 1);
      }
    }

    return true;
  }

  private boolean isValid(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) == s.charAt(right)) {
        left++;
        right--;
      } else {
        return false;
      }
    }

    return true;
  }
}
