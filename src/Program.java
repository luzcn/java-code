import datastructure.BinaryTree.BinaryTree;
import leetcode.ConvertBSTtoGreaterTree;
import leetcode.TreeNode;

public class Program {

    public static void main(String[] args) {

        ConvertBSTtoGreaterTree cs = new ConvertBSTtoGreaterTree();

        BinaryTree bt = new BinaryTree();
        TreeNode root = bt.constructBinaryTree("5,2,13,1");

        cs.convertBST(root);

        bt.preOrderIterative(root);

    }

}