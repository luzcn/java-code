package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance.
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * For example, given three people living at (0,0), (0,4), and (2,2):
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 */
public class BestMeetingPoint {

    // The problem is using Manhattan Distance,
    // so the best meeting point (x, y)
    // x = median of all columns, where the value of cell is 1
    // y = median of all rows, where the values of cell is 1
    public int minTotalDistance(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        ArrayList<Integer> rows = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }

        ArrayList<Integer> columns = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    columns.add(j);
                }
            }
        }

        int x = rows.get(rows.size() / 2);
        int y = columns.get(columns.size() / 2);

        return getDistance(rows, x) + getDistance(columns, y);
    }

    private int getDistance(List<Integer> points, int coordinate) {
        int totalDistance = 0;
        for (int p : points) {
            totalDistance += Math.abs(p - coordinate);
        }

        return totalDistance;
    }

}
