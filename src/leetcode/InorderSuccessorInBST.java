package leetcode;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 */
public class InorderSuccessorInBST {

    // if the gieven node p has right child, then the left-most node in its right sub tree is the successor
    // otherwise, if p is the left child of its parent, then this parent is the successor
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if (root == null || p == null) {
            return null;
        }

        // if p has right child, find the left-most node in the right sub tree
        if (p.right != null) {
            TreeNode successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            return successor;
        }

        // the successor is the parent of p, if p is the left child of its parent.
        TreeNode x = p;
        TreeNode y = getParent(root, x, null);

        // if p is the right child of its parent, need to keep finding its parent,
        // util the parent is nullptr or it is left child.
        while (y != null && y.right == x) {
            x = y;
            y = getParent(root, y, null);
        }

        return y;

    }

    private TreeNode getParent(TreeNode node, TreeNode target, TreeNode parent) {
        if (node == null) {
            return null;
        }

        if (node == target) {
            return parent;
        }

        TreeNode leftSub = getParent(node.left, target, node);
        TreeNode rightSub = getParent(node.right, target, node);

        if (leftSub != null) {
            return leftSub;
        } else {
            return rightSub;
        }
    }
}
