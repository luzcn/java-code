package datastructure.BinaryTree;

import java.util.*;

import leetcode.TreeNode;

public class BinaryTree {

    /**
     * bfs traverse tree
     */
    public void bfs(TreeNode root) {
        // using a queue
        if (root == null) {
            return;
        }

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
     * dfs recursive tree
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
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;

        while (true) {
            if (current != null) {
                stack.addLast(current);
                current = current.left;
            } else if (!stack.isEmpty()) {
                current = stack.removeLast();
                System.out.println(current.val);

                current = current.right;
            } else {
                break;
            }
        }
    }

    /**
     * pre-order traverse iterative solution
     * <p>
     * use stack to simulate the recursive
     * <p>
     * 1. if the stack is not empty, pop the top element
     * 2. save the right node if not null then left node
     */
    public void preOrderIterative(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.println(current.val);

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

    }


    /**
     * Post order non-recursive traverse
     * <p>
     * Similar to pre-order, but push left child first
     * <p>
     * after save the traverse result in a list, reverse it and return
     */
    public List<Integer> postOrderIterative(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        // also use stack
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            res.add(current.val);

            // push the left child first
            if (current.left != null) {
                stack.push(current.left);
            }

            if (current.right != null) {
                stack.push(current.right);
            }
        }

        // reverse the node traverse sequence
        Collections.reverse(res);
        return res;
    }

    /**
     * Level order
     * <p>
     * similar to BFS, but use two queue to save the level information
     */
    public List<List<Integer>> binaryTreeLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

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

    /**
     * Check if a Binary Search Tree is valid
     */
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

                if (prev != null && prev.val >= current.val) {
                    return false;
                }

                prev = current;
                current = current.right;
            } else {
                done = true;
            }
        }
        return true;
    }


    /**
     * Delete Node from BinarySearch Tree
     */
    public TreeNode deleteNode(TreeNode node, int target) {

        if (node == null) {
            return null;
        }

        if (node.val == target) {
            if (node.right != null) {
                TreeNode current = node.right;
                while (current.left != null) {
                    current = current.left;
                }
                current.left = node.left;

                return node.right;
            } else {
                return node.left;
            }
        }

        node.left = deleteNode(node.left, target);
        node.right = deleteNode(node.right, target);

        return node;
    }

    /**
     * construct a binary tree as leetcode  style
     */
    public TreeNode constructBinaryTree(String s) {
        // split by none-word
        String[] data = s.split(",");

        if (data.length == 0) {
            return null;
        }

        TreeNode current = null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        queue.add(root);

        for (int i = 1; i < data.length; i++) {

            TreeNode node = null;
            if (!data[i].equals("null")) {
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