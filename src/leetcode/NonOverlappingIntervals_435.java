package leetcode;

import java.util.*;

// Given a collection of intervals,
// find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
//
// Note:
// You may assume the interval's end point is always bigger than its start point.
// Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
// Example 1:
// Input: [ [1,2], [2,3], [3,4], [1,3] ]
//
// Output: 1
//
// Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

public class NonOverlappingIntervals_435 {


    public int eraseOverlapIntervals(Interval[] intervals) {

        // sort the intervals on start
        // use greedy to find overlap
        // for each pair of intervals, there are 2 case:
        // 1. it1 and it2 has overlap, it1.end > it2.start && it1.end < it2.end, => we keep use it1, remove it2
        // 2. it1 contains it2, it1.end > it2.end => we should remove it1 and use it2,
        //    thus, we will have more space in future to make next intervals non-overlap
        // 3. it1, it2 has no overlap, => simply use it2 and no need to remove

        int n = intervals.length;
        if (n == 0) {
            return 0;
        }

        Arrays.sort(intervals, (x, y) -> x.start - y.start);

        Interval prev = intervals[0];
        int count = 0;

        for (int i = 1; i < n; i++) {

            if (prev.end > intervals[i].start) {
                // overlap
                if (prev.end >= intervals[i].end) {
                    // prev contains current interval
                    // remove prev, use current interval
                    prev = intervals[i];
                }
                count++;
            } else {
                prev = intervals[i];
            }
        }

        return count;
    }


    private class Interval {

        int start;
        int end;
    }
}
