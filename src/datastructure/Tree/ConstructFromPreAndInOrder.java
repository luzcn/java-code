package datastructure.Tree;

import java.util.HashMap;
import java.util.Map;
import leetcode.TreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note: You may assume that duplicates do not exist in the tree.
 */
//  For example, given
//
//   preorder = [3,9,20,15,7]
//   inorder = [9,3,15,20,7]
//   Return the following binary tree:
//
//     3
//    / \
//    9  20
//   /    \
//  15     7
public class ConstructFromPreAndInOrder {

  // map the node value and index in in-order array
  private Map<Integer, Integer> map = new HashMap<>();

  /**
   * Recursively construct a binary tree from pre-order and in-order arrays
   *
   * @param preorder the pre-order traversal array
   * @param inorder the in-order traversal array
   * @param preIndex the current index in pre-order array
   * @param size the left/right children nodes size of current node preorder[preIndex] if size == 0,
   * means no children node, return null
   * @param offset indicate how may nodes have been process in previous iterations
   * @return tree root node
   */
  private TreeNode buildRec(int[] preorder, int[] inorder, int preIndex, int size, int offset) {

    if (size == 0) {
      return null;
    }

    // we consider preorder[preIndex] is the root node of it's sub tree
    int value = preorder[preIndex];

    // the index of this root node in the in-order array
    // the nodes in inorder [offset...rootPosition-1] are the left sub tree node
    // the nodes [rootPosition + 1... size-1] are the right sub tree node.
    int rootPosition = map.get(value) - offset;

    TreeNode node = new TreeNode(value);

    node.left = buildRec(preorder, inorder, preIndex + 1, rootPosition, offset);

    node.right = buildRec(preorder,
        inorder,
        preIndex + rootPosition + 1,
        size - rootPosition - 1,
        offset + rootPosition + 1);

    return node;

  }

  // 在pre-order上建立树， l 和 r的作用是判断 pre[index]是否还有left 和 right children
  private TreeNode dfs(int[] preorder, int[] inorder, int index, int l, int r) {
    if (l > r) {
      return null;
    }

    if (index >= preorder.length) {
      return null;
    }

    int value = preorder[index];
    int rootPos = map.get(value);

    TreeNode node = new TreeNode(value);

    node.left = dfs(preorder, inorder, index + 1, l, rootPos - 1);
    node.right = dfs(preorder, inorder, index + rootPos - l + 1, rootPos + 1, r);

    return node;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length != inorder.length) {
      return null;
    }

    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }

    // build the tree in recursive
    // return buildRec(preorder, inorder, 0, inorder.length, 0);
    return dfs(preorder, inorder, 0, 0, inorder.length - 1);
  }


}
