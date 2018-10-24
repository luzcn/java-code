package leetcode;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and
 * ignoring cases.
 *
 * For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a palindrome.
 */
public class ValidPalindrome {

  public boolean isPalindrome(String s) {
    s = s.trim();

    if (s.isEmpty()) {
      return false;
    }

    int begin = 0;
    int end = s.length() - 1;

    while (begin < end) {
      if (!Character.isAlphabetic(s.charAt(begin)) && !Character.isDigit(s.charAt(begin))) {
        begin++;
        continue;
      }

      if (!Character.isAlphabetic(s.charAt(end)) && !Character.isDigit(s.charAt(end))) {
        end--;
        continue;
      }

      if (Character.toLowerCase(s.charAt(begin)) == Character.toLowerCase(s.charAt(end))) {
        begin++;
        end--;
      } else {
        return false;
      }
    }

    return true;
  }
}
