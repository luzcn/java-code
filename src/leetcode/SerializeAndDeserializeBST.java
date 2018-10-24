package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeBST {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {

    // using level order idea
    Queue<TreeNode> queue = new LinkedList<>();
    List<String> res = new ArrayList<>();

    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      if (current == null) {
        res.add("#");
        continue;
      }

      res.add(current.val + "");

      queue.add(current.left);
      queue.add(current.right);
    }

    return String.join(",", res);

  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String dataStr) {
    String[] data = dataStr.split(",");
    if (data.length == 0 || data[0].equals("#")) {
      return null;
    }

    TreeNode root = new TreeNode(Integer.parseInt(data[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    TreeNode current = null;
    for (int i = 1; i < data.length; i++) {
      TreeNode node = null;

      if (!data[i].equals("#")) {
        node = new TreeNode(Integer.parseInt(data[i]));
      }

      if (current == null) {
        current = queue.poll();
        current.left = node;
      } else {
        current.right = node;
        current = null;
      }

      if (node != null) {
        queue.add(node);
      }
    }

    return root;

  }

}
