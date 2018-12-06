package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Implement a MyCalendarThree class to store your events. A new event can always be added.
//
// Your class will have one method, book(int start, int end).
// Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
//
// A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
//
// For each call to the method MyCalendar.book,
// return an integer K representing the largest integer such that there exists a K-booking in the calendar.
public class MyCalendar3_732 {


  // O(nlogn), similar to meeting rooms
  public int book(int start, int end) {

    starts.add(start);
    ends.add(end);

    starts.sort(Comparator.comparingInt(x -> x));
    ends.sort(Integer::compareTo);
    int i = 0;
    int j = 0;
    int overlap = 0;
    int res = 0;

    while (i < starts.size() && j < ends.size()) {
      if (starts.get(i) < ends.get(j)) {
        i++;
        overlap++;
      } else {
        j++;
        overlap--;
      }

      res = Math.max(res, overlap);
    }

    return res;
  }

  private List<Integer> starts = new ArrayList<>();
  private List<Integer> ends = new ArrayList<>();

}
