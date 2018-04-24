
import leetcode.SparseMatrixMultiplication;

public class Program {

    public static void main(String[] args) {
        SparseMatrixMultiplication p = new SparseMatrixMultiplication();
        int[][] A = {{1, 0, 0},
                {-1, 0, 3}};
        int[][] B = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};

        int[][] s = p.multiply(A, B);

        for (int[] t : s) {
            for (int n : t) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
