package leetcode;

import java.util.*;

// Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it.
// If no such solution, return -1.
//
// For example, with A = "abcd" and B = "cdabcdab".
//
// Return 3, because by repeating A three times (“abcdabcdabcd”),
// B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
//
// Note:
// The length of A and B will be between 1 and 10000.
public class RepeatedStringMatch_686 {

  // brute force solution
  // keep appending A to S, until S.length > B.length && check if S+A contains B
  public int repeatedStringMatch(String A, String B) {

    StringBuilder sb = new StringBuilder(A);
    int count = 1;

    while (sb.indexOf(B) < 0) {

      if (sb.length() > B.length() && sb.append(A).indexOf(B) < 0) {

        // no need to keep appending
        return -1;
      }

      sb.append(A);
      count++;
    }

    return count;
  }


}
