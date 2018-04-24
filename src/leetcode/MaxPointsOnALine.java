package leetcode;

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
    // hash map
    // a line can be described as x = k * y + a
    // k is the slope and a can be ignored here.
    // for points [x1,y1], [x2,y2] and [x3,y3] if we have y2-y1/x2-x1 == y3-y1/x3-x1,
    // then these 3 points are in one line.
    // use a hash map to save these slopes.
    public int maxPoints(Point[] points) {

    }

    private class Point {

        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
