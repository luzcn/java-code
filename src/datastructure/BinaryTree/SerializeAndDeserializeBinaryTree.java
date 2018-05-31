package datastructure.BinaryTree;

import java.util.*;

import leetcode.TreeNode;

// Serialization is the process of converting a data structure or object into a sequence of bits
// so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
// Design an algorithm to serialize and deserialize a binary tree.
//
// There is no restriction on how your serialization/deserialization algorithm should work.
//
// You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
// Example:
//
// You may serialize the following tree:
//
//     1
//    / \
//   2   3
//      / \
//     4   5
//
// as "[1,2,3,null,null,4,5]"
public class SerializeAndDeserializeBinaryTree {

    private int index = 0;

    private void preorder(TreeNode node, List<String> data) {
        if (node == null) {
            data.add("#");
            return;
        }

        data.add(node.val + "");

        preorder(node.left, data);
        preorder(node.right, data);
    }

    private TreeNode deserializeRec(String[] data) {
        if (index >= data.length) {
            return null;
        }

        if (data[index].equals("#")) {
            index++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(data[index]));
        index++;

        node.left = deserializeRec(data);
        node.right = deserializeRec(data);

        return node;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> data = new ArrayList<>();

        preorder(root, data);

        return String.join(",", data);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return this.deserializeRec(data.split(","));
    }
}
