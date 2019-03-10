package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// Design a dataStack that supports push, pop, top, and retrieving the minimum element in constant startTime.
// push(x) -- Push element x onto dataStack.
// pop() -- Removes the element on top of the dataStack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the dataStack.
//
public class MinStack {

  private Deque<Integer> dataStack;
  private Deque<Integer> minStack;

  // initialize your data structure here.
  public MinStack() {

    dataStack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }

  public void push(int x) {

    dataStack.addLast(x);

    if (minStack.isEmpty() || minStack.getLast() <= x) {
      minStack.addLast(x);
    }
  }

  public void pop() {
    int value = dataStack.getLast();
    dataStack.removeLast();

    if (value == getMin()) {
      minStack.removeLast();
    }
  }

  public int top() {
    return dataStack.getLast();
  }

  public int getMin() {
    return minStack.getLast();
  }
}
