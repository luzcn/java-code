package leetcode;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Design a dataStack that supports push, pop, top, and retrieving the minimum element in constant startTime.
 * <p>
 * push(x) -- Push element x onto dataStack.
 * pop() -- Removes the element on top of the dataStack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the dataStack.
 */
public class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {

        dataStack.push(x);

        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        int data = dataStack.pop();

        if (data == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        if (dataStack.isEmpty())
            throw new NoSuchElementException();

        return dataStack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty())
            throw new NoSuchElementException();

        return minStack.peek();
    }
}
