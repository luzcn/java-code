package leetcode;


import java.util.HashMap;

// In the "100 game," two players take turns adding, to a running total, any integer from 1..10.
// The player who first causes the running total to reach or exceed 100 wins.
//
// What if we change the game so that players cannot re-use integers?
//
// For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
//
// Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win,
// assuming both players play optimally.
//
// You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
//
// Example
//
// Input:
// maxChoosableInteger = 10
// desiredTotal = 11
//
// Output:
// false
//
// Explanation:
// - No matter which integer the first player choose, the first player will lose.
// - The first player can choose an integer from 1 up to 10.
// - If the first player choose 1, the second player can only choose integers from 2 up to 10.
// - The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
// - Same with other integers chosen by the first player, the second player will always win.
public class CanIWin_464 {

  // https://leetcode.com/problems/can-i-win/discuss/95277/java-solution-using-hashmap-with-detailed-explanation
  // dfs + memoization
  // the key problem is what would be the key of hashmap
  // - we need to remember the state, which number haven't been used.
  // - if we use a simple boolean array and convert to string, it gives LTE
  // - we use a single integer and the corresponding bit to indicate if the number has been used.
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

    used = new boolean[maxChoosableInteger + 1];

    if (desiredTotal <= maxChoosableInteger) {
      return true;
    }

    int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
    if (sum < desiredTotal) {
      return false;
    }

    return dfs(desiredTotal);
  }

  private boolean[] used;
  private HashMap<Integer, Boolean> map = new HashMap<>();

  private boolean dfs(int desiredTotal) {
    if (desiredTotal <= 0) {
      return false;
    }

    int state = getState();
    if (map.containsKey(state)) {
      return map.get(state);
    }

    for (int i = 1; i < used.length; i++) {
      if (used[i]) {
        continue;
      }

      used[i] = true;

      if (!dfs(desiredTotal - i)) {
        used[i] = false;
        map.put(state, true);
        return true;
      }

      used[i] = false;
      map.put(state, false);
    }

    return map.get(state);
  }


  private int getState() {
    int state = 0;

    for (boolean v : used) {
      state <<= 1;

      if (v) {
        state |= 1;
      }
    }

    return state;
  }
}
