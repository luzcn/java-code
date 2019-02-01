package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

// Given a list of non negative integers, arrange them such that they form the largest number.
//
// For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
//
// Note: The result may be very large, so you need to return a string instead of an integer.
public class LargestNumber_179 {

  public String largestNumber(int[] nums) {

    int[] s = Arrays.stream(nums)
        .boxed()
        .sorted((a, b) -> {
          String str1 = "" + a + b;
          String str2 = "" + a + b;

          // in descending order
          return str2.compareTo(str1);
        })
        .mapToInt(i -> i)
        .toArray();

    StringBuilder res = new StringBuilder();

    for (int i : s) {
      res.append(i);
    }

    return res.charAt(0) == '0' ? "0" : res.toString();
  }

  // use double linked + insertion sort idea
  public String largestNumber2(int[] nums) {
    LinkedList<Integer> sortedNums = new LinkedList<>();

    for (int a : nums) {

      if (sortedNums.isEmpty()) {
        sortedNums.add(a);
        continue;
      }

      boolean isInserted = false;
      for (int i = 0; i < sortedNums.size(); i++) {

        int b = sortedNums.get(i);

        String str1 = "" + a + b;
        String str2 = "" + b + a;

        if (str1.compareTo(str2) > 0) {
          sortedNums.add(i, a);
          isInserted = true;
          break;
        }
      }

      if (!isInserted) {
        sortedNums.addLast(a);
      }
    }

    StringBuilder res = new StringBuilder();

    for (int n : sortedNums) {
      res.append(n);
    }
    return res.charAt(0) == '0' ? "0" : res.toString();

  }
}
