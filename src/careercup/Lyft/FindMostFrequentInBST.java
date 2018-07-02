package careercup.Lyft;

import java.util.*;

import leetcode.TreeNode;


// leetcode 501 https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
// Find the most frequent element in a binary search tree.
// left child <= root <= right child
public class FindMostFrequentInBST {

    // in-order traverse and count the equivalent node numbers

    private int count = 1;
    private int maxFreq = 1;
    private List<Integer> res = new ArrayList<>();
    private TreeNode prev = null;

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);

        if (prev != null) {
            if (prev.val == node.val) {
                count++;
            } else {
                count = 0;
            }
        }

        if (count > maxFreq) {
            res.clear();
            maxFreq = count;
            res.add(node.val);
        } else if (count == maxFreq) {
            res.add(node.val);
        }

        inOrder(node.right);
    }

    public int[] findMoseFrequent(TreeNode root) throws RuntimeException {

        if (root == null) {
            throw new RuntimeException("not valid");
        }

        return res.stream().mapToInt(x -> x).toArray();
    }
}
