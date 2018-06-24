package leetcode;

import java.util.*;

// We are given a list schedule of employees, which represents the working time for each employee.
//
// Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
//
// Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
//
// Example 1:
// Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
// Output: [[3,4]]
// Explanation:
// There are a total of three employees, and all common
// free time intervals would be [-inf, 1], [3, 4], [10, inf].
// We discard any intervals that contain inf as they aren't finite.
//
// Example 2:
// Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
// Output: [[5,6],[7,9]]
// (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
// For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
//
// Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
public class EmployeeFreeTime_759 {

    // line sweeping, find the non-overlapping range
    // similar to meeting rooms 2
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        for (List<Interval> intervals : schedule) {
            for (Interval it : intervals) {
                starts.add(it.start);
                ends.add(it.end);
            }
        }

        starts.sort((x, y) -> x - y);
        ends.sort((x, y) -> x - y);

        List<Interval> res = new ArrayList<>();
        int overlap = 0;

        int i = 0;
        int j = 0;

        while (i < starts.size() && j < ends.size()) {
            if (starts.get(i) < ends.get(j)) {
                overlap++;
                i++;
            } else {
                overlap--;

                if (overlap == 0) {

                    if (!starts.get(i).equals(ends.get(j))) {
                        // [9,9] is not a valid result, although it is not an overlap
                        res.add(new Interval(j, i));
                    }
                }
                j++;
            }
        }

        return res;
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
