import careercup.BinaryTree.BinaryTree;
import leetcode.FindLeavesOfBinaryTree;
import leetcode.TreeNode;

public class Program {


    public static void main(String[] args) {
        FindLeavesOfBinaryTree fs = new FindLeavesOfBinaryTree();

        BinaryTree bt = new BinaryTree();

        TreeNode n = bt.constructBinaryTree("1,2,3,4,5");

        fs.findLeaves(null);
    }
}
