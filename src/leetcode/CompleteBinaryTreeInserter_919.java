package leetcode;

import java.util.*;

// A complete binary tree is a binary tree in which every level, except possibly the last,
// is completely filled, and all nodes are as far left as possible.
//
// Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:
//
// CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
// CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v
// so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
//
// CBTInserter.get_root() will return the head node of the tree.
//
//
// Example 1:
//
// Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
// Output: [null,1,[1,2]]
// Example 2:
//
// Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
// Output: [null,3,4,[1,2,3,4,5,6,7,8]]
public class CompleteBinaryTreeInserter_919 {

  private int height = 1;
  // private TreeNode current = null;
  private TreeNode root;
  private int size = 0;

  private int getSize(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftSub = getSize(node.left);
    int rightSub = getSize(node.right);

    return leftSub + rightSub + 1;
  }


  private TreeNode getCurrent(TreeNode node, int level) {
    if (node == null) {
      return null;
    }

    if ((node.left == null || node.right == null) && level != height) {

      return node;
    }

    TreeNode leftSub = getCurrent(node.left, level + 1);
    TreeNode rightSub = getCurrent(node.right, level + 1);

    if (leftSub != null) {
      return leftSub;
    } else {
      return rightSub;
    }
  }

  public CompleteBinaryTreeInserter_919(TreeNode root) {
    this.root = root;

    TreeNode node = root;
    while (node.left != null) {
      height++;
      node = node.left;
    }

    // get the total number of nodes
    size = getSize(root);

    // the tree is full tree, increase the height
    if (size == Math.pow(2, height) - 1) {
      height++;
    }

  }

  public int insert(int v) {

    // get the first treenode, that has no left or right child
    // and the node level is less than the max height
    TreeNode current = getCurrent(root, 1);

    if (current.left == null) {
      current.left = new TreeNode(v);
    } else {
      current.right = new TreeNode(v);
    }

    size++;
    if (size == Math.pow(2, height) - 1) {
      // the tree is full, increase the height
      height++;
    }
    return current.val;

  }

  public TreeNode get_root() {
    return root;
  }
}
