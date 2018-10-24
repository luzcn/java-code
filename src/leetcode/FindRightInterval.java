package leetcode;

import java.util.Arrays;
import java.util.Comparator;

// Given a set of intervals, for each of the interval i,
// check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i,
// which can be called that j is on the "right" of i.
//
// For any interval i, you need to store the minimum interval j's index,
// which means that the interval j has the minimum start point to build the "right" relationship for interval i.
//
// If the interval j doesn't exist, store -1 for the interval i.
//
// Finally, you need output the stored value of each interval as an array.
//
// Note:
// You may assume the interval's end point is always bigger than its start point.
// You may assume none of these intervals have the same start point.
//
// Example 1:
// Input: [ [1,2] ]
// Output: [-1]
// Explanation: There is only one interval in the collection, so it outputs -1.
//
// Example 2:
// Input: [ [3,4], [2,3], [1,2] ]
// Output: [-1, 0, 1]
// Explanation: There is no satisfied "right" interval for [3,4].
// For [2,3], the interval [3,4] has minimum-"right" start point;
// For [1,2], the interval [2,3] has minimum-"right" start point.
//
// Example 3:
// Input: [ [1,4], [2,3], [3,4] ]
// Output: [-1, 2, -1]
// Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
// For [2,3], the interval [3,4] has minimum-"right" start point.
public class FindRightInterval {

  // binary search idea,
  // 1. construct a special object which contains the interval information and the corresponding index in the original array
  // 2. sort the object list by start time
  // 3. for each interval from original array, do binary search in the sorted object
  // O(nlogn) time
  public int[] findRightInterval(Interval[] intervals) {
    int n = intervals.length;
    if (n == 0) {
      return new int[0];
    }

    // construct a special object which contains the interval information and the corresponding index in the original array
    int[] res = new int[n];
    IntervalData[] dataList = new IntervalData[n];
    for (int i = 0; i < n; i++) {
      dataList[i] = new IntervalData(intervals[i].start, intervals[i].end, i);
    }

    // sort by start time
    Arrays.sort(dataList, Comparator.comparingInt(x -> x.start));

    for (int i = 0; i < n; i++) {
      res[i] = search(dataList, intervals[i].end);
    }

    return res;
  }

  // binary search, find the smallest number which is >= target
  private int search(IntervalData[] dataList, int target) {
    int l = 0;
    int r = dataList.length - 1;
    int minStartTime = Integer.MAX_VALUE;
    int intervalIndex = 0;

    while (l <= r) {
      int mid = l + (r - l) / 2;
      IntervalData m = dataList[mid];

      if (m.start < target) {
        l = mid + 1;
      } else {

        // find a candidate interval
        // keep searching and save the interval which has minimum start time
        if (m.start < minStartTime) {
          minStartTime = m.start;
          intervalIndex = m.index;
        }
        r = mid + 1;
      }
    }

    return minStartTime == Integer.MAX_VALUE ? -1 : intervalIndex;
  }

  private class IntervalData {

    int start;
    int end;
    int index;

    IntervalData(int s, int e, int i) {
      start = s;
      end = e;
      index = i;
    }
  }

  private class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }
}
