package leetcode;

import java.util.Arrays;

// Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
//
// Now, you are given positions of houses and heaters on a horizontal line,
// find out minimum radius of heaters so that all houses could be covered by those heaters.
//
// So, your input will be the positions of houses and heaters seperately,
// and your expected output will be the minimum radius standard of heaters.
//
// Note:
// - Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
// - Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
// - As long as a house is in the heaters' warm radius range, it can be warmed.
// - All the heaters follow your radius standard and the warm radius will the same.
//
// Example 1:
// Input: [1,2,3],[2]
// Output: 1
// Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard,
// then all the houses can be warmed.
//
// Example 2:
// Input: [1,2,3,4],[1,4]
// Output: 1
// Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard,
// then all the houses can be warmed.
public class Heaters_475 {

  // Example:    h = house,  * = heater  M = INT_MAX
  //
  //         h   h   h   h   h   h   h   h   h    houses
  //         1   2   3   4   5   6   7   8   9    index
  //         *           *       *                heaters
  //
  //         0   2   1   0   1   0   -   -   -    (distance to nearest RHS heater)
  //         0   1   2   0   1   0   1   2   3    (distance to nearest LHS heater)
  //
  //         0   1   1   0   1   0   1   2   3    (res = minimum of above two)
  //
  // Result is maximum value in res, which is 3.
  public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(houses);
    Arrays.sort(heaters);

    int m = houses.length;
    int n = heaters.length;

    int[] res = new int[m];
    Arrays.fill(res, Integer.MAX_VALUE);

    int i = 0;
    int h = 0;

    // For each house, calculate distance to nearest RHS heater
    while (i < m && h < n) {

      if (houses[i] <= heaters[h]) {
        res[i] = heaters[h] - houses[i];
        i++;
      } else {
        h++;
      }
    }

    i = m - 1;
    h = n - 1;
    // For each house, calculate distance to nearest LHS heater
    while (i >= 0 && h >= 0) {
      if (houses[i] >= heaters[h]) {
        res[i] = Math.min(res[i], houses[i] - heaters[h]);
        i--;
      } else {
        h--;
      }
    }

    return Arrays.stream(res).max().getAsInt();
  }
}
