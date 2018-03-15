import leetcode.CourseSchedule2;

public class Program {

    public static void main(String[] args) {
        // -1, 0, 1, 2, -1, -4

        CourseSchedule2 cs = new CourseSchedule2();
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}, {4, 5}};

        int[] res = cs.findOrder(8, edges);

    }
}
