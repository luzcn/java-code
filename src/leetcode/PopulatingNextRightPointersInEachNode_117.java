package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PopulatingNextRightPointersInEachNode_117 {

  private List<List<TreeLinkNode>> res = new ArrayList<>();

  // level order, O(n) space
  private void levelOrder(TreeLinkNode node, int level) {
    if (node == null) {
      return;
    }

    if (res.size() == level) {
      res.add(new ArrayList<>());
    }

    List<TreeLinkNode> current = res.get(level);
    if (current.size() > 0) {
      current.get(current.size() - 1).next = node;
    }

    current.add(node);

    levelOrder(node.left, level + 1);
    levelOrder(node.right, level + 1);
  }


  private void dfs(TreeLinkNode node) {

    if (node == null) {
      return;
    }

    // if leaf node, directly return
    if (node.left == null && node.right == null) {
      return;
    }

    TreeLinkNode current = node.left;
    // if left != null and right != null, make left.next = right
    // and point current to right
    // if left == null, move current to right
    if (node.left != null && node.right != null) {
      node.left.next = node.right;
      current = node.right;
    } else if (node.left == null) {
      current = node.right;
    }

    TreeLinkNode p = node.next;
    while (p != null) {
      if (p.left != null) {
        current.next = p.left;
        break;
      }

      if (p.right != null) {
        current.next = p.right;
        break;
      }
      p = p.next;
    }

    dfs(node.right);
    dfs(node.left);
  }

  public void connect(TreeLinkNode root) {
    // level order solution   O(n) space

    dfs(root);
  }


  private class TreeLinkNode {

    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
      val = x;
    }
  }
}
