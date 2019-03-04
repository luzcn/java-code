package leetcode;

// Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
//
// Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
//
// - If S[i] == "I", then A[i] < A[i+1]
// - If S[i] == "D", then A[i] > A[i+1]

import java.util.ArrayList;
import java.util.List;

public class DIStringMatch_942 {

  public int[] diStringMatch(String S) {

    // use the binary search idea
    // low = 0, high = N;
    // if we have s[i] is 'I', we put res[i] as low++;
    // otherwise, res[i]= high--;

    int N = S.length();
    int[] res = new int[N + 1];
    int low = 0;
    int high = N;

    for (int i = 0; i < N; i++) {
      if (S.charAt(i) == 'D') {
        res[i] = high--;
      } else {
        res[i] = low++;
      }
    }
    res[N] = high; // low should == high

    return res;

  }

  public List<Integer> res = new ArrayList<>();

  private void dfs(int[] nums, String S, List<Integer> current, boolean[] visited) {
    if (nums.length == current.size()) {

      if (isValid(S, current)) {
        res = new ArrayList<>(current);
      }
      return;
    }

    for (int i = 0; i < nums.length; i++) {

      if (visited[i]) {
        continue;
      }

      visited[i] = true;
      current.add(nums[i]);

      dfs(nums, S, current, visited);

      current.remove(current.size() - 1);
      visited[i] = false;
    }
  }

  private boolean isValid(String S, List<Integer> nums) {
    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == 'D' && nums.get(i) <= nums.get(i + 1)) {
        return false;
      }

      if (S.charAt(i) == 'I' && nums.get(i) >= nums.get(i + 1)) {
        return false;
      }
    }

    return true;
  }

}
