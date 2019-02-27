package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Given a time represented in the format "HH:MM",
// form the next closest time by reusing the current digits.
//
// There is no limit on how many times a digit can be reused.
//
// You may assume the given input string is always valid.
// For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
//
// Example 1:
//
// Input: "19:34"
// Output: "19:39"
// Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
// It is not 19:33, because this occurs 23 hours and 59 minutes later.
// Example 2:
//
// Input: "23:59"
// Output: "22:22"
// Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
// It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
public class NextClosestTime_681 {

  public String nextClosestTime(String time) {

    HashSet<Character> set = new HashSet<>();

    for (char c : time.toCharArray()) {
      if (c == ':') {
        continue;
      }

      set.add(c);
    }

    List<Character> digits = new ArrayList<>(set);

    // represent the time in minutes
    int minute = 60 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3));

    System.out.println(minute);
    permutations(digits, "", minute);

    return res;
  }

  private String res = "";
  private int minDiff = Integer.MAX_VALUE;

  public List<String> data = new ArrayList<>();

  private void permutations(List<Character> digits, String current, int target) {
    if (current.length() == 4) {

      if (isValid(current)) {

        data.add(current);

        int currentTime =
            60 * Integer.parseInt(current.substring(0, 2)) + Integer.parseInt(current.substring(2));

        //  int diff = currentTime - target > 0 ? currentTime - target : 1440 + currentTime - target;
        int diff = currentTime - target;
        if (diff <= 0) {
          diff += 24 * 60;
        }

        if (diff < minDiff) {
          minDiff = diff;

          res = current.substring(0, 2) + ':' + current.substring(2);
        }
      }
      return;
    }

    for (int i = 0; i < digits.size(); i++) {
      permutations(digits, current + digits.get(i), target);
    }
  }

  public boolean isValid(String chars) {

    if (chars.length() < 4) {
      return false;
    }

    if (chars.charAt(0) > '2') {
      return false;
    }

    if (chars.charAt(0) == '2' && chars.charAt(1) > '3') {
      return false;
    }

    if (chars.charAt(2) > '5') {
      return false;
    }

    return true;
  }
}
