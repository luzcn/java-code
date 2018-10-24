package leetcode;

import java.util.HashMap;
import java.util.HashSet;

// Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
//
// Example 1:
// Given points = [[1,1],[-1,1]], return true.
//
// Example 2:
// Given points = [[1,1],[-1,-1]], return false.
//
// Follow up:
// Could you do better than O(n2)?
//
// Hint:
// - Find the smallest and largest x-value for all points.
// - If there is a line then it should be at y = (minX + maxX) / 2.
// - For each point, make sure that it has a reflected point in the opposite side.

public class LineReflection {

  public boolean isReflected(int[][] points) {

    if (points.length == 0) {
      return true;
    }

    HashMap<Integer, HashSet<Double>> map = new HashMap<>();
    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    for (int[] p : points) {
      minX = Math.min(p[0], minX);
      maxX = Math.max(p[0], maxX);
      map.computeIfAbsent(p[0], k -> new HashSet<>()).add((double) p[1]);
    }

    double line = (double) (minX + maxX) / 2;
    for (int[] p : points) {
      int x = p[0];
      int y = p[1];

      int oppositeX = (int) (2 * line - x);

      if (!map.containsKey(oppositeX) || !map.get(oppositeX).contains((double) y)) {
        return false;
      }
    }

    return true;

  }
}
