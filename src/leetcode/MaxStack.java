package leetcode;

import java.util.Stack;

// Design a max stack that supports push, pop, top, peekMax and popMax.
// push(x) -- Push element x onto stack.
//
// pop() -- Remove the element on top of the stack and return it.
//
// top() -- Get the element on the top.
//
// peekMax() -- Retrieve the maximum element in the stack.
//
// popMax() -- Retrieve the maximum element in the stack, and remove it.
// If you find more than one maximum elements, only remove the top-most one.
//
// NOTE:
// 1. -1e7 <= x <= 1e7
// 2. Number of operations won't exceed 10000.
// 3. The last four operations won't be called when stack is empty.
//
// Example 1:
// MaxStack stack = new MaxStack();
// stack.push(5);
// stack.push(1);
// stack.push(5);
// stack.top(); -> 5
// stack.popMax(); -> 5
// stack.top(); -> 1
// stack.peekMax(); -> 5
// stack.pop(); -> 1
// stack.top(); -> 5
//
public class MaxStack {

  private Stack<Integer> dataStack;
  private Stack<Integer> tempStack;
  private Stack<Integer> maxStack;

  /////
  // initialize your data structure here.
  ///
  public MaxStack() {
    this.dataStack = new Stack<>();
    this.tempStack = new Stack<>();
    this.maxStack = new Stack<>();
  }

  public void push(int x) {

    dataStack.push(x);

    if (maxStack.isEmpty() || maxStack.peek() <= x) {
      maxStack.push(x);
    }
  }

  public int pop() {

    int data = dataStack.pop();

    if (data == maxStack.peek()) {
      maxStack.pop();
    }
    return data;
  }

  public int top() {
    return dataStack.peek();
  }

  public int peekMax() {
    return maxStack.peek();
  }

  public int popMax() {

    int maxData = maxStack.pop();

    while (!dataStack.isEmpty() && dataStack.peek() != maxData) {
      tempStack.push(dataStack.pop());
    }

    if (!dataStack.isEmpty()) {
      dataStack.pop();
    }

    // need to put back to the main data stack
    // if the max stack is empty or the top element is less than data in temp stack,
    // need to add to the temp stack.
    while (!tempStack.isEmpty()) {
      int value = tempStack.pop();
      dataStack.push(value);

      if (maxStack.isEmpty() || maxStack.peek() <= value) {
        maxStack.push(value);
      }
    }

    return maxData;
  }
}

/////
// Your MaxStack object will be instantiated and called as such:
// MaxStack obj = new MaxStack();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.top();
// int param_4 = obj.peekMax();
// int param_5 = obj.popMax();
///
