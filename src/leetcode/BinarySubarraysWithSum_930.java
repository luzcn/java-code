package leetcode;

import java.util.*;

// In an array A of 0s and 1s, how many non-empty subarrays have sum S?
//
//
//
// Example 1:
//
// Input: A = [1,0,1,0,1], S = 2
// Output: 4
// Explanation:
// The 4 subarrays are bolded below:
// [{1,0,1},0,1]
// [{1,0,1,0},1]
// [1,{0,1,0,1}]
// [1,0,{1,0,1}]

public class BinarySubarraysWithSum_930 {

  // two pointer
  public int numSubarraysWithSum(int[] A, int S) {
    int n = A.length;
    int i = 0;
    int begin = 0;
    int sum = 0;
    int res = 0;

    while (i < n) {
      sum += A[i];

      if (sum < S) {
        i++;
        continue;
      }

      if (sum == S) {
        int before = 0;
        int end = 1;

        while (begin < i && A[begin] == 0) {
          before++;
          begin++;
        }

        i++;
        while (i < n && A[i] == 0) {
          end++;
          i++;
        }

        res += before * end;
        begin++;
        sum--;
      }

    }

    return res;
  }

  // brute force, can AC in leetcode
  // O(n^2)
  public int numSubarraysWithSumBruteForce(int[] A, int S) {

    int n = A.length;
    int res = 0;

    for (int i = 0; i < n; i++) {
      int sum = 0;
      for (int j = i; j < n; j++) {
        sum += A[j];

        if (sum > S) {
          break;
        }

        if (sum == S) {
          res++;
        }
      }
    }

    return res;
  }
}
