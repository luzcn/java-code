package leetcode;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i,
 * ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *
 * Find two lines, which together with x-axis forms a container, such that the container contains
 * the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {

  // brute force solution takes O(n^2)

  // use two pointer idea, let begin = 0, end = n - 1
  // now the container [begin, end] may not have the largest height, but have the longest width
  // move the min(height[begin], height[end]) index, repeat this step until begin == end
  // this take O(n) time

  public int maxArea(int[] height) {
    if (height.length < 2) {
      return 0;
    }

    int res = Integer.MIN_VALUE;
    int begin = 0;
    int end = height.length - 1;

    while (begin < end) {
      int area = (end - begin) * Math.min(height[begin], height[end]);
      res = Math.max(res, area);

      if (height[begin] < height[end]) {
        begin++;
      } else {
        end--;
      }

    }

    return res;
  }

}
