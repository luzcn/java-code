package leetcode;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, find the leftmost value in the last row of the tree.
//
// Example 1:
// Input:
//
//     2
//    / \
//   1   3
//
// Output:
// 1
// Example 2:
// Input:
//
//         1
//        / \
//       2   3
//      /   / \
//     4   5   6
//        /
//       7
//
// Output:
// 7
public class FindBottomLeftTreeValue_513 {

  // private List<List<Integer>> res = new ArrayList<>();

  //     private void dfs(TreeNode node, int level) {
  //         if (node == null) {
  //             return;
  //         }

  //         if (res.size() == level) {
  //             res.add(new ArrayList<>());
  //         }

  //         res.get(level).add(node.val);

  //         dfs(node.left, level + 1);
  //         dfs(node.right, level + 1);
  //     }

  private int bfs(TreeNode root) {

    List<List<TreeNode>> res = new ArrayList<>();
    List<TreeNode> queue = new ArrayList<>();
    List<TreeNode> temp = new ArrayList<>();

    queue.add(root);

    while (!queue.isEmpty()) {
      res.add(new ArrayList<>(queue));

      for (TreeNode node : queue) {
        if (node.left != null) {
          temp.add(node.left);
        }

        if (node.right != null) {
          temp.add(node.right);
        }
      }

      queue = temp;
      temp = new ArrayList<>();
    }

    return res.get(res.size() - 1).get(0).val;
  }

  public int findBottomLeftValue(TreeNode root) {
    //         dfs(root, 0);
    //         return res.get(res.size() - 1).get(0);

    return bfs(root);
  }
}
