package leetcode;

import java.util.Iterator;
import java.util.List;

// Given a nested list of integers, implement an iterator to flatten it.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Example 1:
// Given the list [[1,1],2,[1,1]],
//
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
//
// Example 2:
// Given the list [1,[4,[6]]],
//
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

public class NestedIterator implements Iterator<Integer> {

  // use two variable: int index and "NestedIterator currentIterator"
  // 1. save the given List<NestedInteger> into a member field "ni".
  // 2. if ni[index] is an integer, keep the currentIterator as null
  // 3. when call the next() function, need to move currentIterator to next NestedInteger which is non-empty list
  // so the hasNext condition is index < ni.length && (currentIterator != null && currentIterator.hasNext());

  private NestedInteger[] ni;
  private NestedIterator currentIterator;
  private int index;

  public NestedIterator(List<NestedInteger> nestedList) {
    // ni = (NestedInteger[]) nestedList.toArray(new NestedInteger[nestedList.size()]);
    int n = nestedList.size();

    ni = new NestedInteger[n];
    for (int i = 0; i < n; i++) {
      ni[i] = nestedList.get(i);
    }

    index = 0;
    currentIterator = null;

    moveToNext();
  }


  private void moveToNext() {
    while (index < ni.length && !ni[index].isInteger()) {

      // make the current iterator point to the current  reading list
      // if the current reading list is a single integer, keep the current iterator as null
      NestedIterator next = new NestedIterator(ni[index].getList());

      if (next.hasNext()) {
        currentIterator = next;
        break;
      }
      index++;
    }
  }

  @Override
  public Integer next() {
    Integer value = null;
    if (currentIterator != null) {
      value = currentIterator.next();

      if (currentIterator.hasNext()) {
        // the current list is not finished reading yet,
        // no need to update current iterator
        return value;
      }
    } else {
      // current iterator is null
      value = ni[index].getInteger();
    }

    currentIterator = null;
    index++;
    moveToNext();

    return value;
  }

  @Override
  public boolean hasNext() {

    return this.index < this.ni.length || (currentIterator != null && currentIterator.hasNext());
  }

  private interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();
  }
}