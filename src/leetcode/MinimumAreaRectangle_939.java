package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Given a set of points in the xy-plane,
// determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
//
// If there isn't any rectangle, return 0.
//
//
//
// Example 1:
//
// Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
// Output: 4
// Example 2:
//
// Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
// Output: 2
public class MinimumAreaRectangle_939 {

  // time: O(n^3) but can accepted by LC
  public int minAreaRect(int[][] points) {

    HashMap<Integer, List<Integer>> mapX = new HashMap<>();
    // HashMap<Integer, List<Integer>> mapY = new HashMap<>();
    HashSet<String> set = new HashSet<>();
    int res = Integer.MAX_VALUE;

    for (int[] point : points) {
      int x = point[0];
      int y = point[1];
      mapX.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
      // mapY.computeIfAbsent(y, k -> new ArrayList<>()).add(x);

      set.add(x + "#" + y);
    }

    for (int x : mapX.keySet()) {
      List<Integer> rows = mapX.get(x);

      Collections.sort(rows);

      for (int i = 0; i < rows.size(); i++) {
        for (int j = i + 1; j < rows.size(); j++) {
          int y1 = rows.get(i);
          int y2 = rows.get(j);



        }
      }
    }

    // for (int[] point : points) {
    //   int x1 = point[0];
    //   int y1 = point[1];
    //
    //   if (mapX.get(x1).size() == 1) {
    //     continue;
    //   }
    //
    //
    //   for (int x2 : mapY.get(y1)) {
    //     if (x2 == x1) {
    //       continue;
    //     }
    //     for (int y2 : mapX.get(x1)) {
    //       if (y2 == y1) {
    //         continue;
    //       }
    //
    //       if (set.contains(x2 + "#" + y2)) {
    //         int area = Math.abs(x2 - x1) * Math.abs(y2 - y1);
    //         res = Math.min(res, area);
    //       }
    //     }
    //   }
    // }

    return res == Integer.MAX_VALUE ? 0 : res;
  }
}
