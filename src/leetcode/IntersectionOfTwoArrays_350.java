package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Given two arrays, write a function to compute their intersection.
//
// Example:
// Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
//
// Note:
// Each element in the result should appear as many times as it shows in both arrays.
// The result can be in any order.
//
// Follow up:
// - What if the given array is already sorted? How would you optimize your algorithm?
// - What if nums1's size is small compared to nums2's size? Which algorithm is better?
// - What if elements of nums2 are stored on disk, and the memory is limited
//   such that you cannot load all elements into the memory at once?
public class IntersectionOfTwoArrays_350 {

  // use hash map
  public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int n : nums1) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }

    List<Integer> res = new ArrayList<>();
    for (int n : nums2) {

      map.computeIfPresent(n, (k, v) -> {
        if (v > 0) {
          res.add(k);
        }
        return v - 1;
      });

      // if (map.getOrDefault(n, 0) > 0) {
      //   res.add(n);
      //   map.put(n, map.get(n) - 1);
      // }
    }

    return res.stream().mapToInt(x -> x).toArray();
  }


  // the arrays are sorted
  public int[] intersectSortedArrays(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    int i = 0;
    int j = 0;

    ArrayList<Integer> res = new ArrayList<>();

    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        res.add(nums1[i]);
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {
        j++;
      }
    }

    return res.stream().mapToInt(x -> x).toArray();
  }
}
