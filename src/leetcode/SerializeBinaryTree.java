package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so
 * that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm should work.
 * <p>
 * You just need to ensure that a binary tree can be serialized to a string and this string can be
 * deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following tree
 * <p>
 * NOTE: Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeBinaryTree {

  private static final String EMPTY = "null";
  private static final String SPLITTER = ",";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    preOrder(root, sb);

    return sb.toString();
  }

  private void preOrder(TreeNode node, StringBuilder sb) {

    if (node == null) {
      sb.append(EMPTY).append(SPLITTER);
      return;
    }

    sb.append(node.val).append(SPLITTER);
    preOrder(node.left, sb);
    preOrder(node.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    Deque<String> nodes = new LinkedList<>();
    nodes.addAll(Arrays.asList(data.split(SPLITTER)));

    if (nodes.isEmpty()) {
      return null;
    }

    return this.buildTree(nodes);
  }

  private TreeNode buildTree(Deque<String> nodes) {
    String val = nodes.remove();
    TreeNode node = null;

    if (!val.equals(EMPTY)) {
      node = new TreeNode(Integer.parseInt(val));
      node.left = buildTree(nodes);
      node.right = buildTree(nodes);
    }

    return node;
  }

}
