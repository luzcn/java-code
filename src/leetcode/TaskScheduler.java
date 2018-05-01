package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 *
 * Tasks could be done without original order.
 * Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */
public class TaskScheduler {

    // similar to Rearrange String K Distance Apart
    public int leastInterval(char[] tasks, int n) {

        int length = tasks.length;

        int ans = tasks.length;
        // count the task frequency
        HashMap<Character, Integer> map = new HashMap<>();
        for (char t : tasks) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        PriorityQueue<Character> heap = new PriorityQueue<>((c1, c2) -> {
            if (map.get(c1).equals(map.get(c2))) {
                return c1.compareTo(c2);
            } else {
                return map.get(c2) - map.get(c1);
            }
        });

        // add all task to heap
        heap.addAll(map.keySet());

        while (!heap.isEmpty()) {

            ArrayList<Character> temp = new ArrayList<>();
            // int len = Math.min(length, n + 1);

            for (int i = 0; i < n + 1; i++) {
                if (heap.isEmpty()) {
                    ans++;
                    continue;
                }

                char c = heap.poll();
                map.put(c, map.get(c) - 1);

                if (map.get(c) > 0) {
                    temp.add(c);
                }

                // decreased one task frequency, so need to shrink the original tasks length
                // when finished processing all the tasks, it is not necessary to append extra "idle"
                if (--length == 0) {
                    break;
                }
            }

            heap.addAll(temp);
        }

        return ans;
    }
}
