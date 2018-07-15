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
                //
                // 由于通过斜率来判断共线需要用到除法，而用double表示的双精度小数在有的系统里不一定准确，
                // 为了更加精确无误的计算共线，我们应当避免除法，从而避免无线不循环小数的出现，
                //
                // 那么怎么办呢，我们把除数和被除数都保存下来，不做除法，但是我们要让这两数分别除以它们的最大公约数，这样例如8和4，4和2，2和1，
                // 这三组商相同的数就都会存到一个映射里面，同样也能实现我们的目标，
                //
                // 而求GCD的函数如果用递归来写那么一行就搞定了
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
