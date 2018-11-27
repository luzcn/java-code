package leetcode;

// Given an integer n, find the closest integer (not including itself), which is a palindrome.
//
// The 'closest' is defined as absolute difference minimized between two integers.
//
// Example 1:
// Input: "123"
// Output: "121"
// Note:
// The input n is a positive integer represented by string, whose length will not exceed 18.
// If there is a tie, return the smaller one as answer.
public class FindTheClosestPalindrome_564 {

  public String nearestPalindromic(String n) {

    return nearestPalindromicBF(n);
  }

  private String nearestPalindromicBF(String n) {
    Long num = Long.parseLong(n);

    for (int i = 1; i < num; i++) {

      String s1 = String.valueOf(num - i);
      String s2 = String.valueOf(num + i);
      if (isPalindrome(s1)) {
        return s1;
      }

      if (isPalindrome(s2)) {
        return s2;
      }
    }

    return n;
  }

  private boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;

    while (i <= j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }
}
