import leetcode.GraphValidTree;

public class Program {

    public static void main(String[] args) {
        // -1, 0, 1, 2, -1, -4

        GraphValidTree ms = new GraphValidTree();
        int[][] edges = new int[][]{{2, 3}, {1, 2}, {1, 3}};

        System.out.println(ms.validTree(4, edges));
    }
}
