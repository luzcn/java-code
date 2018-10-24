package leetcode;

// Given a positive integer n, return the number of all possible attendance records with length n,
// which will be regarded as rewardable.
//
// The answer may be very large, return it after mod 109 + 7.
//
// A student attendance record is a string that only contains the following three characters:
//
// 'A' : Absent.
// 'L' : Late.
// 'P' : Present.
// A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
//
// Example 1:
// Input: n = 2
// Output: 8
//
// Explanation:
// There are 8 records with length 2 will be regarded as rewardable:
// "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
// Only "AA" won't be regarded as rewardable owing to more than one absent times.
public class StudentAttendanceRecord_552 {

  public int checkRecord(int n) {

    // DP solution

    return 0;
  }

  // 551 You need to return whether the student could be rewarded according to his attendance record.
  public boolean checkRecord1(String s) {
    int countAbsent = 0;
    int countLate = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == 'A') {
        countAbsent++;
        if (countAbsent > 1) {
          return false;
        }
      } else if (c == 'L') {
        if (i > 0 && s.charAt(i - 1) == 'L') {
          countLate++;

          if (countLate > 1) {
            return false;
          }
        } else {
          countLate = 0;
        }
      }
    }

    return true;
  }
}
