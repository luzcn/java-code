package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Different from the previous question where weight is increasing from root to leaf,
// now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
//
//Example 1:
//
//Input: [[1,1],2,[1,1]]
//Output: 8
//Explanation: Four 1's at depth 1, one 2 at depth 2.
//Example 2:
//
//Input: [1,[4,[6]]]
//Output: 17
//Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
public class NestedListWeightSum2_364 {

  public int depthSumInverse(List<NestedInteger> nestedList) {

    // the problem asks to calculate in reverse depth order,
    // we can first save the NestedInteger into a HashMap, where key is the depth index and value is the level sum

    dfs(nestedList, 0);

    int sum = 0;

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

      sum += (maxLevel - entry.getKey() + 1) * entry.getValue();
    }

    return sum;
  }

  private HashMap<Integer, Integer> map = new HashMap<>();
  private int maxLevel = 0;

  private void dfs(List<NestedInteger> nestedList, int level) {
    maxLevel = Math.max(maxLevel, level);
    for (NestedInteger node : nestedList) {
      if (node.isInteger()) {
        map.put(level, map.getOrDefault(level, 0) + node.getInteger());
      } else {
        dfs(node.getList(), level + 1);
      }
    }
  }

  interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }
}
