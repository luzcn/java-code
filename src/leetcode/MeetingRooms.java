package leetcode;

import java.util.Arrays;
import java.util.Comparator;

// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
// determine if a person could attend all meetings.
//
// For example,
// Given [[0, 30],[5, 10],[15, 20]],
// return false.
//
// Thoughts:
// The solution is sort the intervals by start time, and check are there any overlaps
///
public class MeetingRooms {

  public boolean canAttendMeetings(Interval[] intervals) {

    // sort by start time
    Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i].end > intervals[i + 1].start) {
        return false;
      }
    }

    return true;
  }


  // Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
  // find the minimum number of conference rooms required.
  //
  // For example,
  // Given [[0, 30],[5, 10],[15, 20]],
  // return 2.
  //
  // Thoughts:
  // The solution is to find the maximum overlaps among all these interval, or if no overlap, we need at least 1 room
  // 1. construct 2 arrays S and E, one contains all the start points, another holds all end points
  // 2. sort them
  // 3. iterate through the array,
  // - if we find S[i] < E[j], it means we find an interval or an overlap, we increase the overlap count
  // - if S[i]<= E[j], one interval is finished, decrease the overlap
  // 4. the min room number should be the max value of overlaps.
  ///
  public int minMeetingRooms(Interval[] intervals) {
    int n = intervals.length;
    int[] starts = new int[n];
    int[] ends = new int[n];
    int minRoom = 0;

    for (int i = 0; i < n; i++) {
      starts[i] = intervals[i].start;
      ends[i] = intervals[i].end;
    }

    Arrays.sort(starts);
    Arrays.sort(ends);

    // it can be an overlap or an interval
    int overlap = 0;
    int i = 0, j = 0;

    // after iteration, overlap should be at least 1
    while (i < n && j < n) {
      if (starts[i] < ends[j]) {
        overlap++;
        i++;
      } else {
        overlap--;
        j++;
      }

      minRoom = Math.max(minRoom, overlap);
    }

    return minRoom;
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
