package careercup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    /**
     * bfs traverse tree
     */
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

    /**
     * dfs tree
     */
    public void dfs(TreeNode root) {
        // pre-order
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        dfs(root.left);
        dfs(root.right);
    }

    /**
     * In-order traverse iterative solution
     */
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

    /**
     * pre-order traverse iterative solution
     */
    public void preOrderIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();

        stack.add(root);

        while (!stack.isEmpty()){
            TreeNode current = stack.pop();
            System.out.println(current.val);

            if (current.right != null){
                stack.push(current.right);
            }

            if (current.left != null){
                stack.push(current.left);
            }
        }

    }

    public List<List<Integer>> binaryTreeLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        Queue<TreeNode> que = new LinkedList<>();
        Queue<TreeNode> queTemp = new LinkedList<>();
        List<Integer> levelList = new ArrayList<>();

        while (!que.isEmpty()) {
            TreeNode current = que.poll();
            levelList.add(current.val);

            if (current.left != null) {
                queTemp.add(current.left);
            }

            if (current.right != null) {
                queTemp.add(current.right);
            }

            if (que.isEmpty()) {
                que = queTemp;
                result.add(levelList);

                queTemp = new LinkedList<>();
                levelList = new ArrayList<>();
            }
        }
        return result;
    }

    public boolean isValidBST(TreeNode root) {

        // use in-order iterative traverse
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