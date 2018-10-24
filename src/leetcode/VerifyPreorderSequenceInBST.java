package leetcode;

import java.util.Stack;

// Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//
// You may assume each number in the sequence is unique.
//
// Consider the following binary search tree:
//
//      5
//     / \
//    2   6
//   / \
//  1   3
// Example 1:
//
// Input: [5,2,6,1,3]
// Output: false
// Example 2:
//
// Input: [5,2,1,3,6]
// Output: true
//
// Thought:
// Kinda simulate the traversal,
// keeping a stack of nodes (just their values) of which we're still in the left subtree.
//
// If the next number is smaller than the last stack value,
// then we're still in the left subtree of all stack nodes,
// so just push the new one onto the stack.
//
// But before that, pop all smaller ancestor values,
// as we must now be in their right subtrees (or even further, in the right subtree of an ancestor).
//
// Also, use the popped values as a lower bound,
// since being in their right subtree means we must never come across a smaller number anymore.
public class VerifyPreorderSequenceInBST {

  // iterate the pre-order array from the second element,i.e. i = 1
  // the first is always root element nothing to compare
  // 1. use a stack to save  the element
  // 2. if we see a right node e.g. A[i] > A[i-1],
  // - if A[i] is less than stack top element, it is still in the left sub tree, just push to the stack
  // - if A[i] is larger than top element, it is right node of some element in the stack,
  //  we need to repeat pop it and save as the "MinValue"
  // - if we found any element after this i, smaller than this MinValue, return false.
  // because after this "right node" all element should be larger

  public boolean verifyPreorder(int[] preorder) {

    if (preorder.length < 2) {
      return true;
    }

    Stack<Integer> stack = new Stack<>();
    int n = preorder.length;
    int minValue = Integer.MIN_VALUE;

    // start from the 2nd element,
    // the first is should be the root, need to comparing A[i] and A[i-1]
    int index = 1;
    stack.push(preorder[0]);

    while (index < n) {
      if (preorder[index] < minValue) {
        // once we found a right node, we will update the minValue
        // if we found a number smaller than this minValue, this breaks the bst
        return false;
      }

      // found a right node
      while (!stack.isEmpty() && stack.peek() < preorder[index]) {
        // if the input preorder is valid, all elements after this A[i]
        // should larger than numbers in this stack which are less than A[i],
        // because they are parent and left sibling of A[i]
        //
        // so we need to save the minValue and if we found any number smaller than it after this A[i],
        // we consider it's not valid
        minValue = stack.pop();
      }

      stack.push(preorder[index++]);
    }

    return true;
  }


  // O(1) space, re-use the given pre-order array
  public boolean verifyPreorderNoStack(int[] preorder) {
    int minValue = Integer.MIN_VALUE;
    int i = -1;

    for (int p : preorder) {
      if (p < minValue) {
        return false;
      }
      while (i >= 0 && p > preorder[i]) {
        minValue = preorder[i--];
      }
      preorder[++i] = p;
    }
    return true;
  }
}
