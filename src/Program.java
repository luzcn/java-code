import datastructure.Tree.BinaryTree;
import leetcode.IncreasingOrderSearchTree_897;

public class Program {

  public static void main(String[] args) {
    IncreasingOrderSearchTree_897 sol = new IncreasingOrderSearchTree_897();

    var root = BinaryTree.constructBinaryTree("5,3,6,2,4,null,8,1,null,null,null,7,9");

    BinaryTree.inOrderIterative(root);

  }


}