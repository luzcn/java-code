package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new ArrayList<>();

        for (Interval it : intervals) {
            if (newInterval.start > it.end || it.start > newInterval.end) {
                // no overlap
                result.add(new Interval(it.start, it.end));
            } else {
                newInterval = new Interval(Math.min(it.start, newInterval.start), Math.max(it.end, newInterval.end));
            }
        }

        result.add(newInterval);
        result.sort(Comparator.comparingInt(l -> l.start));

        return result;
    }

    private class Interval {

        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
