package leetcode;

import java.util.*;

// Given a nested list of integers represented as a string, implement a parser to deserialize it.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Note: You may assume that the string is well-formed:
//
// String is non-empty.
// String does not contain white spaces.
// String contains only digits 0-9, [, - ,, ].
// Example 1:
//
// Given s = "324",
//
// You should return a NestedInteger object which contains a single integer 324.
public class MiniParser_385 {

    public NestedInteger deserialize(String s) {
        // use stack
        if (s.length() == 0) {
            return new NestedInteger();
        }

        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        int start = 1;

        for (int i = 0; i < s.length(); i++) {
            // if current char is [, need to create a new NestedInteger object and push to the stack
            if (s.charAt(i) == '[') {
                start = i + 1;
                stack.push(new NestedInteger());
            } else if (s.charAt(i) == ',' || s.charAt(i) == ']') {

                // add the number into the stack.peek() NestedInteger object
                if (i > start) {
                    stack.peek().add(new NestedInteger(Integer.parseInt(s.substring(start, i))));
                }

                start = i + 1;

                // the current NestedInteger is a list and finished, need to remove it from the stack,
                // and add back to the stack.peek();
                if (s.charAt(i) == ']') {
                    NestedInteger top = stack.pop();

                    if (!stack.isEmpty()) {
                        stack.peek().add(top);
                    } else {
                        stack.push(top);
                    }
                }
            }
        }

        return stack.peek();
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    private class NestedInteger {

        // Constructor initializes an empty nested list.
        NestedInteger() {
        }

        // Constructor initializes a single integer.
        NestedInteger(int value) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }


        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 0;
        }


        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }


        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }


        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }
}
