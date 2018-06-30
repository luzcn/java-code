package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/////
// Given a char array representing tasks CPU need to do.
// It contains capital letters A to Z where different letters represent different tasks.
//
// Tasks could be done without original order.
// Each task could be done in one interval.
// For each interval, CPU could finish one task or just be idle.
//
// However, there is a non-negative cooling interval n that means between two same tasks,
// there must be at least n intervals that CPU are doing different tasks or just be idle.
//
// You need to return the least number of intervals the CPU will take to finish all the given tasks.
//
// Example 1:
// Input: tasks = ["A","A","A","B","B","B"], n = 2
// Output: 8
// Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
///
public class TaskScheduler {

    // similar to Rearrange String K Distance Apart
    // use a max heap, ordered by the character frequency
    public int leastInterval(char[] tasks, int n) {

        int length = tasks.length;

        int idles = 0;
        // count the task frequency
        HashMap<Character, Integer> map = new HashMap<>();
        for (char t : tasks) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        // add all task to heap
        heap.addAll(map.values());

        while (!heap.isEmpty()) {

            // use to save the next round tasks
            ArrayList<Integer> temp = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                if (heap.isEmpty()) {
                    idles++;
                    continue;
                }

                int freq = heap.poll() - 1;
                if (freq > 0) {
                    temp.add(freq);
                }

                // decreased one task frequency, so need to shrink the original tasks length
                // when finished processing all the tasks, it is not necessary to append extra "idle"
                if (--length == 0) {
                    break;
                }
            }

            // need to add the reaming tasks back to heap,
            // for next round schedule.
            heap.addAll(temp);
        }

        return idles + tasks.length;
    }


    // output the schedule list
    public String schedule(char[] tasks, int n) {
        StringBuilder res = new StringBuilder();

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Data> queue = new PriorityQueue<>((x, y) -> y.freq - x.freq);
        int length = tasks.length;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new Data(entry.getKey(), entry.getValue()));
        }

        while (!queue.isEmpty()) {
            List<Data> temp = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                if (queue.isEmpty()) {
                    res.append('#');
                    continue;
                }

                Data task = queue.poll();
                res.append(task.id);
                task.freq--;
                if (task.freq > 0) {
                    temp.add(task);
                }

                if (--length == 0) {
                    break;
                }
            }

            queue.addAll(temp);
        }
        // System.out.println(res.toString());

        return res.toString();
    }

    private class Data {

        char id;
        int freq;

        Data(char t, int f) {
            id = t;
            freq = f;
        }
    }
}
