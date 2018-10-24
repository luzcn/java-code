package leetcode;

// You have a list of points in the plane.
// Return the area of the largest triangle that can be formed by any 3 of the points.
//
// Example:
// Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
// Output: 2
// Explanation:
// The five points are show in the figure below. The red triangle is the largest.
public class LargestTriangleArea {

  public double largestTriangleArea(int[][] points) {

    if (points.length < 3) {
      return 0;
    }
    int n = points.length;
    double max_area = 0;
    // Heron's formula
    for (int i = 0; i < n - 2; i++) {
      for (int j = i + 1; j < n - 1; j++) {
        double a = euclideanDistance(points[i], points[j]);

        for (int k = j + 1; k < n; k++) {
          double b = euclideanDistance(points[i], points[k]);
          double c = euclideanDistance(points[j], points[k]);

          if (isValidTriangle(a, b, c)) {
            double S = (a + b + c) / 2;
            max_area = Math.max(max_area, Math.sqrt(S * (S - a) * (S - b) * (S - c)));
          }
        }
      }
    }

    return max_area;
  }

  private boolean isValidTriangle(double a, double b, double c) {
    return a + b > c && b + c > a && c + a > b;
  }

  private double euclideanDistance(int[] p, int[] q) {
    int x = p[0] - q[0];
    int y = p[1] - q[1];

    return Math.sqrt(x * x + y * y);
  }
}
