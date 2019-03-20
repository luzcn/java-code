package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KClosestPointsToOrigin_973 {

  // use tree map

  public int[][] kClosest(int[][] points, int k) {
    Map<Double, List<int[]>> map = new TreeMap<>();

    for (int[] p : points) {
      int x = p[0];
      int y = p[1];

      double distance = Math.sqrt(x * x + y * y);

      map.computeIfAbsent(distance, key -> new ArrayList<>()).add(p);
    }

    int[][] res = new int[k][2];
    int i = 1;
    for (List<int[]> point : map.values()) {

      for (int[] p : point) {
        if (i > k) {
          break;
        }

        res[i - 1][0] = p[0];
        res[i - 1][1] = p[1];
        i++;
      }
    }

    return res;
  }
}
