package leetcode;

import java.util.HashMap;
import java.util.Map;

// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
//Example 1:
//
//Input: [[1,1],[2,2],[3,3]]
//Output: 3
//Explanation:
//^
//|
//|        o
//|     o
//|  o
//+------------->
//0  1  2  3  4
//Example 2:
//
//Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//Output: 4
//Explanation:
//^
//|
//|  o
//|     o        o
//|        o
//|  o        o
//+------------------->
//0  1  2  3  4  5  6
public class MaxPointsOnALine {

//    private static final double INF = Double.POSITIVE_INFINITY;

    // hash map
    // a line can be described as x = k * y + a
    // k is the slope and a can be ignored here.
    // for points [x1,y1], [x2,y2] and [x3,y3] if we have y2-y1/x2-x1 == y3-y1/x3-x1,
    // then these 3 points are in one line.
    // use a hash map to save these slopes.
    public int maxPoints(Point[] points) {

        Map<String, Integer> map = new HashMap<>();

        int result = 1;

        for (int i = 0; i < points.length - 1; i++) {
            map.clear();
            Point p1 = points[i];
            int duplicate = 0;
            int currentMax = 1;

            for (int j = i + 1; j < points.length; j++) {
                Point p2 = points[j];

                if (p2.x == p1.x && p2.y == p1.y) {
                    duplicate++;
                    continue;
                }

                int dx = p2.x - p1.x;
                int dy = p2.y - p1.y;
                int gcd = this.generateGcd(dx, dy);

                dx /= gcd;
                dy /= gcd;

                // 用string来存储斜率
                String slope = String.valueOf(dx) + String.valueOf(dy);

                map.put(slope, map.getOrDefault(slope, 1) + 1);
                currentMax = Math.max(currentMax, map.get(slope));
            }

            result = Math.max(result, currentMax + duplicate);
        }

        return result;
    }

    private int generateGcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return generateGcd(y, x % y);
    }

    private class Point {

        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
