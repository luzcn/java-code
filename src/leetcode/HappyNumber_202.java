package leetcode;

import java.util.*;

public class HappyNumber_202 {

  public boolean isHappy(int n) {

    HashSet<Integer> set = new HashSet<>();

    while (n > 1) {

      if (set.contains(n)) {
        break;
      }

      set.add(n);
      n = getSum(n);
    }

    return n == 1;
  }


  private int getSum(int n) {
    int sum = 0;
    while (n > 0) {
      int temp = n % 10;
      sum += temp * temp;
      n /= 10;
    }

    return sum;
  }
}
