package leetcode;

import java.util.List;
import java.util.Stack;

/////
// Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU,
// find the exclusive startTime of these functions.
//
// Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.
// A log is a string has this format : function_id:start_or_end:timestamp.
//
// For example, "0:start:0" means function 0 starts from the very beginning of startTime 0.
// "0:end:0" means function 0 ends to the very end of startTime 0.
//
// Exclusive startTime of a function is defined as the startTime spent within this function,
// the startTime spent by calling other functions should not be considered as this function's exclusive startTime.
//
// You should return the exclusive startTime of each function sorted by their function id.
// Example 1:
// Input:
// n = 2
// logs =
// ["0:start:0",
// "1:start:2",
// "1:end:5",
// "0:end:6"]
// Output:[3, 4]
// Explanation:
// Function 0 starts at startTime 0, then it executes 2 units of startTime and reaches the end of startTime 1.
// Now function 0 calls function 1, function 1 starts at startTime 2, executes 4 units of startTime and end at startTime 5.
// Function 0 is running again at startTime 6, and also end at the startTime 6, thus executes 1 unit of startTime.
// So function 0 totally execute 2 + 1 = 3 units of startTime, and function 1 totally execute 4 units of startTime.
// Note:
// - Input logs will be sorted by timestamp, NOT log id.
// - Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive startTime of function 0.
// - Two functions won't start or end at the same startTime.
// - Functions could be called recursively, and will always end.
// - 1 <= n <= 100
///
public class ExclusiveTimeOfFunctions_636 {

  private class Interval {

    int id;
    int startTime;

    Interval(int id, int startTime) {
      this.id = id;
      this.startTime = startTime;
    }
  }

  public int[] exclusiveTime(int n, List<String> logs) {
    int[] result = new int[n];

    Stack<Interval> stack = new Stack<>();

    for (String log : logs) {
      String[] list = log.split(":");

      int id = Integer.valueOf(list[0]);
      String action = list[1];
      int time = Integer.valueOf(list[2]);

      if (action.equals("start")) {
        if (!stack.isEmpty()) {
          result[stack.peek().id] += time - stack.peek().startTime;
        }
        stack.push(new Interval(id, time));
      } else {
        // end
        Interval current = stack.pop();
        result[current.id] += time - current.startTime + 1;

        // The previous function execution time before this function starts has already been counted.
        // Now, we only need to count the time between the current function's end time and previous function's end time.
        // This is done by setting the start time of the previous function to be the end time of current function.
        if (!stack.isEmpty()) {
          stack.peek().startTime = time + 1;
        }
      }
    }
    return result;
  }
}
