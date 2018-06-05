package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 *
 * Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
 * write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 *
 * It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
 *
 * Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline,
 * and always has zero height.
 *
 * Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 */
public class SkyLineProblem {

    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> result = new ArrayList<>();

        if (buildings.length == 0 || buildings[0].length == 0) {
            return result;
        }

        List<Point> height = new ArrayList<>();
        for (int[] build : buildings) {
            height.add(new Point(build[0], build[2], true));
            height.add(new Point(build[1], build[2], false));
        }

        // sort these points, by positions, if they have the same position
        // put the left coordinate ahead
        height.sort((p1, p2) -> {
            if (p1.position != p2.position) {
                return p1.position - p2.position;
            }
            return p1.isLeft ? -1 : 1;
        });

        // max heap, we always track the largest height
        PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> y - x);

        // sorted map, orderd by position, used to remove unnecessary duplicate
        // i.e intpu buildings are [[0,1,1], [1,2,1]],
        // without using this map, will output [0,1], [1,1],[2,0]
        // the [1,1] is redundant
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // don't forget this "0", otherwise we will lose the last endpoint with 0 height
        heap.offer(0);

        for (Point h : height) {
            if (h.isLeft) {
                heap.offer(h.height);
            } else {
                heap.remove(h.height);
            }

            map.put(h.position, heap.peek());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!result.isEmpty() && result.get(result.size() - 1)[1] == entry.getValue()) {
                continue;
            }
            result.add(new int[]{entry.getKey(), entry.getValue()});
        }

        return result;
    }


    private class Point {

        int position;
        int height;
        boolean isLeft;

        Point(int pos, int h, boolean left) {
            position = pos;
            height = h;
            isLeft = left;
        }

    }
}
