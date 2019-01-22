package leetcode;

import java.util.*;

// Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points,
// with sides not necessarily parallel to the x and y axes.
//
// If there isn't any rectangle, return 0.
public class MinimumAreaRectangle2_963 {

  public double minAreaFreeRect(int[][] points) {
    // for (int i = 0; i < points.length-3; i++) {
    //   int[] point1 = points[i];
    //   for (int j = i + 1; j < points.length-2; j++) {
    //     int[] point2 = points[j];
    //     for (int k = j + 1; k < points.length - 1; k++) {
    //       int[] point3 = points[k];
    //
    //       for (int l = k + 1; l < points.length; l++) {
    //         int[] point4 = points[k];
    //       }
    //     }
    //   }
    // }

    return 0;
  }

  // determine if 4 points can form an rectangle
  private boolean canBeRectangle(int[] point1, int[] point2, int[] point3, int[] point4) {
    int x1 = point1[0];
    int y1 = point1[1];

    int x2 = point2[0];
    int y2 = point2[1];

    int x3 = point3[0];
    int y3 = point3[1];

    int x4 = point4[0];
    int y4 = point4[1];

    double cx = (double) (x1 + x2 + x3 + x4) / 4;
    double cy = (double) (y1 + y2 + y3 + y4) / 4;

    double delta1 = (cx - x1) * (cx - x1) + (cy - y1) * (cy - y1);
    double delta2 = (cx - x2) * (cx - x2) + (cy - y2) * (cy - y2);
    double delta3 = (cx - x3) * (cx - x3) + (cy - y3) * (cy - y4);
    double delta4 = (cx - x4) * (cx - x4) + (cy - y3) * (cy - y4);

    return delta1 == delta2 && delta1 == delta3 && delta1 == delta4;
  }
}
