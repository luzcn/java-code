import java.util.*;

import leetcode.EvaluateDivision;

public class Program {

    public static void main(String[] args) {

        EvaluateDivision ed = new EvaluateDivision();

        String[][] equations = new String[][]{{"a", "b"}, {"b", "c"}};
        double[] values = new double[]{2.0, 3.0};
        String[][] queries = new String[][]{{"a", "c"}, {"a", "a"}, {"a", "e"}, {"x", "x"}};

        Arrays.stream(ed.calcEquation(equations, values, queries)).forEach(System.out::println);

    }

}