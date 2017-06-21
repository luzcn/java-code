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
            System.out.println(current.value);

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

        System.out.println(root.value);

        dfs(root.left);
        dfs(root.right);
    }


    public void inorderIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        boolean done = false;

        while (!done) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            }
            else if (!stack.empty()) {
                current = stack.pop();

                System.out.println(current.value);

                current = current.right;
            }
            else {
                done = true;
            }
        }
    }


    public void binaryTreeLevelOrder(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queueLevel1 = new LinkedList<>();
        Queue<TreeNode> queueLevel2 = new LinkedList<>();




    }
}

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }
}