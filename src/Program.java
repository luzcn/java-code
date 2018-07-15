import java.util.*;

import leetcode.DesignLinkedList_707;


public class Program {

    public static void main(String[] args) {
        int[][] p = {{1, 2, 3}, {1, 2}};

        for (int[] s : p) {
            for (int a : s) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}