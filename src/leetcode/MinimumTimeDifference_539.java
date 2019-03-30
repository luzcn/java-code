package leetcode;

import java.util.*;

// Given a list of 24-hour clock time points in "Hour:Minutes" format,
// find the minimum minutes difference between any two time points in the list.
//
// Example 1:
// Input: ["23:59","00:00"]
// Output: 1
public class MinimumTimeDifference_539 {

  // sort
  // the time can find its unique position in the clock
  // so consider as a circular, fist compute the last - 24*60, which is a negative number
  // then, for each time point, compute the time - prev, save the min diff and make prev point to current time.
  public int findMinDifference(List<String> timePoints) {

    if (timePoints.size() > 24 * 60) {
      // there are duplicate time in the given list
      // the diff is 0, directly return
      return 0;
    }

    // sort the given list with string comparison in ascending order
    Collections.sort(timePoints);

    int minDiff = Integer.MAX_VALUE;

    int prev = timeToInt(timePoints.get(timePoints.size() - 1)) - 24 * 60; // is should be a negative value

    for (String time : timePoints) {
      minDiff = Math.min(minDiff, timeToInt(time) - prev);
      prev = timeToInt(time);
    }

    return minDiff;
  }

  private int timeToInt(String s) {
    return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
  }
}
