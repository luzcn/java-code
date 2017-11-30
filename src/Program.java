import leetcode.BoundaryofBinaryTree;
import leetcode.TreeNode;

public class Program {


    public static void main(String[] args) {

        TreeNode[] nodes = {new TreeNode(1),
                new TreeNode(2),
                new TreeNode(3),
                new TreeNode(4)};

        nodes[0].right = nodes[1];
        // nodes[1].left = nodes[2];
        nodes[1].right = nodes[3];

        BoundaryofBinaryTree bbt = new BoundaryofBinaryTree();

        System.out.println(bbt.boundaryOfBinaryTree(nodes[0]));
    }
}
