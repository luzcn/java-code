package leetcode;

import java.util.HashSet;

// Given two strings A and B of lowercase letters,
// return true if and only if we can swap two letters in A so that the result equals B.

// Example 1:
//
// Input: A = "ab", B = "ba"
// Output: true
// Example 2:
//
// Input: A = "ab", B = "ab"
// Output: false
// Example 3:
//
// Input: A = "aa", B = "aa"
// Output: true
// Example 4:
//
// Input: A = "aaaaaaabc", B = "aaaaaaacb"
// Output: true
// Example 5:
//
// Input: A = "", B = "aa"
// Output: false
public class BuddyStrings_859 {

  public boolean buddyStrings(String A, String B) {

    if (A.length() != B.length()) {
      return false;
    }

    // if A == B, check if the string has duplicate chars
    if (A.equals(B)) {
      return hasDuplicate(A);
    }

    int index1 = -1;
    int index2 = -1;

    for (int i = 0; i < A.length(); i++) {
      if (A.charAt(i) != B.charAt(i)) {

        if (index1 == -1) {
          index1 = i;
        } else if (index2 == -1) {
          index2 = i;
        } else {

          // if more than 2 positions are not equivalent return false
          return false;
        }
      }
    }

    // check if the two position can swap and make A, B equivalent
    return A.charAt(index1) == B.charAt(index2) && A.charAt(index2) == B.charAt(index1);
  }

  private boolean hasDuplicate(String s) {
    HashSet<Character> seen = new HashSet<>();
    for (char c : s.toCharArray()) {
      if (seen.contains(c)) {
        return true;
      }

      seen.add(c);
    }
    return false;
  }
}
