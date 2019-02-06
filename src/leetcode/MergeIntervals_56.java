package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Created by zhenlu on 9/22/16.


public class MergeIntervals_56 {

  public List<Interval> merge(List<Interval> intervals) {

    List<Interval> result = new ArrayList<>();

    if (intervals.isEmpty()) {
      return result;
    }

    intervals.sort(Comparator.comparingInt(v -> v.start));
    // intervals.sort((v1, v2) -> v1.start - v2.start);

    Interval first = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      Interval second = intervals.get(i);

      // if overlap, need to merge.
      if (first.start <= second.end && first.end >= second.start) {
        first.start = Math.min(first.start, second.start);
        first.end = Math.max(first.end, second.end);
      } else {
        result.add(new Interval(first.start, first.end));
        first = second;
      }
    }

    // don't forget add the "first" to result list
    result.add(new Interval(first.start, first.end));

    return result;
  }

  private boolean isOverlap(Interval l1, Interval l2) {

    // take the compliment, => l1.end >= l2.start && l1.start <= l2.end
    if (l1.end < l2.start || l1.start > l2.end) {
      return false;
    }

    return true;
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
