import datastructure.Tree.BinaryTree;
import leetcode.PrintBinaryTree_655;
import leetcode.TreeNode;

public class Program {

  public static void main(String[] args) {

    TreeNode root = BinaryTree.constructBinaryTree("1,2");

    PrintBinaryTree_655 printBinaryTree_655 = new PrintBinaryTree_655();

    printBinaryTree_655.printTree(root);

  }
}