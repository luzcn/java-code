package leetcode;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as
 * follow:
 *
 * The root is the maximum number in the array. 1. The left subtree is the maximum tree constructed
 * from left part subarray divided by the maximum number. 2. The right subtree is the maximum tree
 * constructed from right part subarray divided by the maximum number.
 *
 * Construct the maximum tree by the given array and output the root node of this tree.
 */
//Example 1:
// Input: [3,2,1,6,0,5]
// Output: return the tree root node representing the following tree:
//
//       6
//     /   \
//    3     5
//     \    /
//      2  0
//        \
//         1
public class MaximumBinaryTree {

  /**
   * find the maximum number from array, in range[i...j]
   *
   * @param nums input array
   * @param i begin index
   * @param j end index
   * @return an array, first element is max number, second is the index
   */
  private int[] getMaxNum(int[] nums, int i, int j) {

    int maxNum = Integer.MIN_VALUE;
    int[] result = new int[2];

    for (int k = i; k <= j; k++) {
      if (nums[k] > maxNum) {
        maxNum = nums[k];
        result[0] = maxNum;
        result[1] = k;
      }
    }
    return result;
  }

  private TreeNode constructRec(int[] nums, int i, int j) {

    if (i > j) {
      return null;
    }

    if (i == j) {
      return new TreeNode(nums[i]);
    }

    int[] value = getMaxNum(nums, i, j);
    TreeNode node = new TreeNode(value[0]);

    node.left = constructRec(nums, i, value[1] - 1);
    node.right = constructRec(nums, value[1] + 1, j);

    return node;
  }

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return constructRec(nums, 0, nums.length - 1);
  }
}
