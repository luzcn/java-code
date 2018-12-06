package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Implement a MyCalendarTwo class to store your events.
// A new event can be added if adding the event will not cause a triple booking.
public class MyCalendar2_731 {


  // O(n) time
  public boolean book(int start, int end) {
    List<int[]> overlaps = new ArrayList<>();

    for (int[] event : events) {

      // if new event is overlap with some existing events x and y
      // check if the event x is also overlapping with y
      // if y is null or x, y not overlap, save x into the list
      if (isOverlap(event, new int[]{start, end})) {
        for (int[] interval : overlaps) {
          if (isOverlap(event, interval)) {
            return false;
          }
        }
        overlaps.add(event);
      }
    }

    events.add(new int[]{start, end});
    return true;
  }

  private List<int[]> events = new ArrayList<>();

  private boolean isOverlap(int[] l1, int[] l2) {
    return l2[0] < l1[1] && l2[1] > l1[0];
  }


  // O(nlogn) TLE
  public boolean book2(int start, int end) {

    List<Integer> s = new ArrayList<>(starts);
    s.add(start);

    List<Integer> e = new ArrayList<>(ends);
    e.add(end);

    s.sort(Comparator.comparingInt(x -> x));
    e.sort(Integer::compareTo);
    int i = 0;
    int j = 0;
    int overlap = 0;

    while (i < s.size() && j < e.size()) {
      if (s.get(i) < e.get(j)) {
        i++;
        overlap++;
      } else {
        j++;
        overlap--;
      }

      if (overlap == 3) {
        return false;
      }
    }

    starts.add(start);
    ends.add(end);

    return true;
  }

  private List<Integer> starts = new ArrayList<>();
  private List<Integer> ends = new ArrayList<>();


}
