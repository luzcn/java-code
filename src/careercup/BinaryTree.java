package careercup;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    public void bfs(TreeNode root) {
        // using a queue
        if (root == null)
            return;

        Queue<TreeNode> que = new LinkedList<>();

        que.add(root);

        while (!que.isEmpty()) {
            TreeNode current = que.poll();
            System.out.println(current.val);

            if (current.left != null) {
                que.add(current.left);
            }

            if (current.right != null) {
                que.add(current.right);
            }
        }
    }


    public void dfs(TreeNode root) {
        // pre-order
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        dfs(root.left);
        dfs(root.right);
    }

    public void inOrderIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;
        boolean done = false;

        while (!done) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.println(current.val);

                current = current.right;
            } else {
                done = true;
            }
        }
    }


    public void binaryTreeLevelOrder(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> que = new LinkedList<>();
        Queue<TreeNode> queTemp = new LinkedList<>();

        que.add(root);

        while (!que.isEmpty()) {
            TreeNode current = que.poll();

            System.out.println(current.val);

            if (current.left != null) {
                queTemp.add(current.left);
            }

            if (current.right != null) {
                queTemp.add(current.right);
            }

            if (que.isEmpty()) {
                que = queTemp;
                queTemp = new LinkedList<>();
            }
        }
    }

    public boolean isValidBST(TreeNode root) {

        // use in-order iterative solution
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode prev = null;

        boolean done = false;

        while (!done) {
            if (current != null) {
                stack.push(current);

                current = current.left;
            } else if (!stack.isEmpty()) {
                current = stack.pop();

                if (prev != null && prev.val >= current.val)
                    return false;

                prev = current;
                current = current.right;
            } else {
                done = true;
            }
        }
        return true;
    }
}