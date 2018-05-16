import java.util.*;

import leetcode.TreeNode;

public class Program {

    public static void main(String[] args) {

        String s = "apple";
        for (int i = s.length(); i >= 0; i--) {
            System.out.println(s.substring(i) + "{" + s);
        }

        System.out.println(s.indexOf("p"));
    }

}