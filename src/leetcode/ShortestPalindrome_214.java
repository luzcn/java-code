package leetcode;

// Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
// Find and return the shortest palindrome you can find by performing this transformation.
//
// Example 1:
//
// Input: "aacecaaa"
// Output: "aaacecaaa"
// Example 2:
//
// Input: "abcd"
// Output: "dcbabcd"
public class ShortestPalindrome_214 {

  // Thought:
  // Since the problem asks us to add characters infront of the input string,
  // we can find the longest palindrome substring starting from index 0.
  // - Using KMP solution to compute the failure table of string "s + reverse(s)"
  // - or comparing with the reverse of the string, rev.substring(i) == s.substring(0, n - i), which takes O(n^2) time
  public String shortestPalindrome(String s) {

    if (s.isEmpty()) {
      return s;
    }

    String rev = (new StringBuilder(s)).reverse().toString();
    int n = s.length();

    // O(n^2) time, because the string comparison takes O(n)
    for (int i = 0; i < n; i++) {

      // the first matched substring is the longest palindrome substring
      // because this longest palindrome substring must start from index 0
      if (rev.substring(i).equals(s.substring(0, n - i))) {
        return rev.substring(0, i) + s;
      }
    }

    return "";
  }

  public String shortestPalindromeKMP(String s) {
    if (s.isEmpty()) {
      return s;
    }

    String rev = (new StringBuilder(s)).reverse().toString();
    int n = s.length();

    String str = s + "#" + rev;

    // the KMP failure function can find the longest length of prefix substring which is also its suffix
    // in O(n)
    int[] lps = new int[str.length()];
    for (int i = 1; i < lps.length; i++) {
      int t = lps[i - 1];

      while (t > 0 && str.charAt(i) != str.charAt(t)) {
        t = lps[t - 1];
      }

      if (str.charAt(i) == str.charAt(t)) {
        t++;
      }

      lps[i] = t;
    }

    return rev.substring(0, n - lps[str.length() - 1]) + s;
  }
}
