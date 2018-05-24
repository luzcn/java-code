package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are n different online courses numbered from 1 to n.
 * Each course has some duration(course length) t and closed on dth day.
 *
 * A course should be taken continuously for t days and must be finished before or on the dth day.
 * You will start at the 1st day.
 *
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 *
 * Example:
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day,
 * and ready to take the next course on the 101st day.
 *
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day,
 * and ready to take the next course on the 1101st day.
 *
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 */
public class CourseSchedule3 {

    // dfs solution + memoization
    // for each course, we choose take or not take
    // https://leetcode.com/problems/course-schedule-iii/solution/

    private int dfs(int[][] course, int index, int currentTime, int[][] memo) {
        if (index > course.length) {
            return 0;
        }

        if (memo[index][currentTime] > 0) {
            return memo[index][currentTime];
        }

        int take = 0;
        if (currentTime + course[index][0] <= course[index][1]) {
            take = dfs(course, index + 1, currentTime + course[index][0], memo);
        }
        int notTake = dfs(course, index + 1, currentTime, memo);

        memo[index][currentTime] = Math.max(take, notTake);
        return memo[index][currentTime];
    }

    public int scheduleCourseDFS(int[][] courses) {

        // sort the given array by close time
        Arrays.sort(courses, Comparator.comparingInt(x -> x[1]));

        int m = courses.length;
        int closeTime = courses[m - 1][1];

        int[][] memo = new int[m + 1][closeTime + 1];

        return dfs(courses, 0, 0, memo);
    }


    // max heap + greedy
    // 1. sort the given courses by close time,
    // here is a hidden condition: duration <= close time, otherwise the course is not valid.
    // 2. for each course, if the totalTime + current duration <= current course close time, take this course
    // e.g. time += duration, queue.offer(duration).
    // 3. otherwise, if the queue.peek is larger than current duration, pop it, and take current course
    // e.g. time -= queue.peek()
    public int scheduleCourse(int[][] courses) {

        Arrays.sort(courses, Comparator.comparingInt(x -> x[1]));
        int time = 0;

        // use maxHeap
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);

        for (int[] course : courses) {
            if (time + course[0] <= course[1]) {
                queue.offer(course[0]);
                time += course[0];
            } else if (!queue.isEmpty() && queue.peek() > course[0]) {
                // if we previously take a longer duration course,
                // we can choose pop that course and take current course instead.

                time -= queue.poll();

                time += course[0];
                queue.offer(course[0]);
            }
        }

        return queue.size();
    }
}
