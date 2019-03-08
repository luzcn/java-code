package leetcode;

import java.util.Arrays;

// Write a function to find the longest common prefix string amongst an array of strings.
//
// If there is no common prefix, return an empty string "".
//
// Example 1:
//
// Input: ["flower","flow","flight"]
// Output: "fl"
// Example 2:
//
// Input: ["dog","racecar","car"]
// Output: ""
// Explanation: There is no common prefix among the input strings.
// Note:
//
// All given inputs are in lowercase letters a-z.
public class LongestCommonPrefix_14 {

  public String longestCommonPrefix(String[] strs) {

    if (strs == null || strs.length == 0) {
      return "";
    }

    int length = Arrays.stream(strs).mapToInt(String::length).min().getAsInt();

    StringBuilder prefix = new StringBuilder();

    for (int i = 0; i < length; i++) {

      for (int j = 1; j < strs.length; j++) {

        if (strs[j].charAt(i) != strs[j - 1].charAt(i)) {
          return prefix.toString();
        }
      }

      prefix.append(strs[0].charAt(i));
    }

    return prefix.toString();
  }

}
