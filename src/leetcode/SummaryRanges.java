package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a sorted integer array without duplicates, return the summary of its ranges.
//
// Example 1:
//
// Input:  [0,1,2,4,5,7]
// Output: ["0->2","4->5","7"]
// Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
// Example 2:
//
// Input:  [0,2,3,4,6,8,9]
// Output: ["0","2->4","6","8->9"]
// Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
public class SummaryRanges {

  private List<String> res = new ArrayList<>();

  private void buildRange(int[] nums, int begin, int end) {
    if (begin == end - 1) {
      res.add(String.valueOf(nums[begin]));
    } else {
      res.add(String.valueOf(nums[begin]) + "->" + String.valueOf(nums[end - 1]));
    }
  }

  public List<String> summaryRanges(int[] nums) {

    int begin = 0;
    int end = begin + 1;

    while (end < nums.length) {

      if (nums[begin] + (end - begin) == nums[end]) {
        // the input is sorted array without duplicates
        // so if the nums[begin] + distace(end, begin) == nums[end], we consider they in a continuous subarray
        end++;
      } else {
        this.buildRange(nums, begin, end);
        begin = end;
        end++;
      }
    }

    // don't forget the last [begin, end-1] elements
    this.buildRange(nums, begin, end);

    return res;
  }

}
