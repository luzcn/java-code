package leetcode;

import java.util.ArrayList;
import java.util.List;

// The set [1,2,3,...,n] contains a total of n! unique permutations.
//
// By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
//
// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
// Given n and k, return the kth permutation sequence.
//
// Note:
//
// Given n will be between 1 and 9 inclusive.
// Given k will be between 1 and n! inclusive.
// Example 1:
//
// Input: n = 3, k = 3
// Output: "213"
// Example 2:
//
// Input: n = 4, k = 9
// Output: "2314"
public class PermutationSequence_60 {

  public String getPermutation(int n, int k) {
    this.k = k;

    dfs(n, "", new boolean[n + 1]);
    return res;

  }


  private String res = "";
  private int k;

  // dfs recursive solution
  private void dfs(int n, String current, boolean[] visited) {
    if (current.length() == n) {
      if (--k == 0) {
        res = current;
      }
      return;
    }

    for (int i = 1; i <= n; i++) {
      if (visited[i]) {
        continue;
      }

      visited[i] = true;
      dfs(n, current + i, visited);
      visited[i] = false;
    }
  }

  //  // math solution
  //     int factorial(int n)
  //     {
  //         if (n <= 1)
  //             return 1;
  //
  //         return factorial(n - 1)*n;
  //     }
  //
  //     string getPermutation(int n, int k)
  //     {
  //         string result;
  //         k--;
  //         vector<int> nums;
  //
  //         for (int i = 1; i <= n; i++)
  //             nums.push_back(i);
  //
  //         for (int i = n; i >= 1; i--)
  //         {
  //             int base = factorial(i - 1);
  //             int index = k / base;
  //             k = k % base;
  //
  //             result.append(to_string(nums[index]));
  //             nums.erase(nums.begin() + index);
  //         }
  //
  //         return result;
  //     }
}
