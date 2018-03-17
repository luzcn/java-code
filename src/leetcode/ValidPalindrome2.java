package leetcode;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome2 {

    // this question can only delete once, so there are only 2 situation, l++ or r--
    // so, dfs is an overkill
    // we can scan the string twice, similar to the valid palindrome 1 problem.
    // if either iteration is true, return true.
    public boolean validPalindrome(String s) {

        s = s.trim();
        if (s.isEmpty())
            return true;

        int l = 0;
        int r = s.length() - 1;
        boolean canDelete = true;

        boolean result1 = true;

        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {

                l++;
                r--;
            } else if (canDelete) {
                l++;
                canDelete = false;
            } else {
                result1 = false;
                break;
            }
        }

        l = 0;
        r = s.length() - 1;
        canDelete = true;
        boolean result2 = true;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else if (canDelete) {
                r--;
                canDelete = false;
            } else {
                result2 = false;
                break;
            }

        }

        return result1 || result2;
    }
}
