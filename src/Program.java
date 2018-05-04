import java.util.ArrayList;
import java.util.Arrays;

import datastructure.BinaryTree.BinaryTree;
import leetcode.CountUnivalueSubtrees;
import leetcode.TreeNode;

public class Program {

    public static void main(String[] args) {
        CountUnivalueSubtrees sm = new CountUnivalueSubtrees();

        BinaryTree bt = new BinaryTree();
        TreeNode root = bt.constructBinaryTree("5,1,5,5,5,null,5");

        System.out.println(sm.countUnivalSubtrees(root));

        ArrayList<Integer> res = new ArrayList<>();
    }
}