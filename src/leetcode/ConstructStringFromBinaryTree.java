package leetcode;

// You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
//
// The null node needs to be represented by empty parenthesis pair "()".
// And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship
// between the string and the original binary tree.
//
// Example 1:
// Input: Binary tree: [1,2,3,4]
//        1
//      /   \
//     2     3
//    /
//   4
//
// Output: "1(2(4))(3)"
//
// Explanation: Originallay it needs to be "1(2(4)())(3()())",
// but you need to omit all the unnecessary empty parenthesis pairs.
// And it will be "1(2(4))(3)".
// Example 2:
// Input: Binary tree: [1,2,3,null,4]
//        1
//      /   \
//     2     3
//      \
//       4
//
// Output: "1(2()(4))(3)"
//
// Explanation: Almost the same as the first example,
// except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
public class ConstructStringFromBinaryTree {

  // pre-order
  // private String dfs(TreeNode node) {
  //
  //   if (node == null) {
  //     return "";
  //   }
  //
  //   if (node.left == null && node.right == null) {
  //     return Integer.toString(node.val);
  //   }
  //
  //   // if right child is null, we should skip the ()
  //   if (node.right == null) {
  //     return node.val + "(" + dfs(node.left) + ")";
  //   }
  //
  //   return node.val + "(" + dfs(node.left) + ")(" + dfs(node.right) + ")";
  //
  // }

  private String dfs(TreeNode node) {
    if (node == null) {
      return null;
    }

    if (node.left == null && node.right == null) {
      return node.val + "";
    }

    String leftSub = dfs(node.left);
    String rightSub = dfs(node.right);

    return node.val + (leftSub == null ? "()" : "(" + leftSub + ")") + (rightSub == null ? ""
        : "(" + rightSub + ")");
  }

  public String tree2str(TreeNode t) {

    if (t == null) {
      return "";
    }
    return dfs(t);
  }
}
