package leetcode;

import java.util.List;

public class NestedListWeightSum_339 {

  public int depthSum(List<NestedInteger> nestedList) {
    return dfs(nestedList, 1);
  }


  private int dfs(List<NestedInteger> nestedList, int depth) {
    if (nestedList == null) {
      return 0;
    }

    int sum = 0;
    for (NestedInteger n : nestedList) {
      if (n == null) {
        continue;
      }

      if (n.isInteger()) {
        sum += n.getInteger() * depth;
      } else {
        sum += dfs(n.getList(), depth + 1);
      }
    }

    // e.g. [1,[4,[6]]] the totalSum = (1 + 4 + 6) + (4 + 6 ) + (6) == 1*1 + 4*2 + 6*3 == 28
    return sum;
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


