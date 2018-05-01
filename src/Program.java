import leetcode.SpiralMatrix;

public class Program {

    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();

        // int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        int[][] matrix = sm.generateMatrix(4);

        for (int[] s : matrix) {
            for (int i : s) {
                System.out.print(i + "\t\t");
            }
            System.out.println();
        }
    }
}