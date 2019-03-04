package leetcode;

// Given an array of 4 digits, return the largest 24 hour time that can be made.
//
// The smallest 24 hour time is 00:00, and the largest is 23:59.
// Starting from 00:00, a time is larger if more time has elapsed since midnight.
//
// Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
//
//
//
// Example 1:
//
// Input: [1,2,3,4]
// Output: "23:41"
// Example 2:
//
// Input: [5,5,5,5]
// Output: ""
public class LargestTimeForGivenDigits_949 {
  // similar to Next Closest Time problem
  // DFS, get all the combination of given digits
  // if the combination is a valid time, covert to minutes
  // save the max time

  public String largestTimeFromDigits(int[] A) {

    dfs(A, "", new boolean[4]);

    return res;
  }

  private int maxTime = 0;
  private String res = "";

  private void dfs(int[] nums, String s, boolean[] used) {
    if (s.length() == 4) {
      int hour = Integer.parseInt(s.substring(0, 2));
      int minute = Integer.parseInt(s.substring(2));

      if (hour <= 23 && minute <= 59) {
        //maxTime = Math.max(maxTime, hour*60 + minute);
        int currentTime = hour * 60 + minute;
        if (maxTime <= currentTime) {
          maxTime = currentTime;
          res = s.substring(0, 2) + ":" + s.substring(2);
        }
      }

      return;
    }

    for (int i = 0; i < nums.length; i++) {

      if (used[i]) {
        continue;
      }

      used[i] = true;
      dfs(nums, s + nums[i], used);
      used[i] = false;
    }
  }

//   private boolean isValid(String s) {
//     String hour = s.substring(0, 2);
//     String minute = s.substring(2);

//     if (Integer.parstInt(hour) <= 23 && Integer.parseInt(minute) <= 59) {
//       return true;
//     }

//     return false;
//   }

}
