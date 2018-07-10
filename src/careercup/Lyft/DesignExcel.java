package careercup.Lyft;

import java.util.*;

// A1=B1+C1
// B1 = C2 - D1
// C2 = 1
// D1 = -1
// C1 = 12
public class DesignExcel {

    private HashMap<String, String> graph = new HashMap<>();
    private HashMap<String, Integer> memo = new HashMap<>();
    List<String> expressions;

    public DesignExcel(List<String> expressions) {
        this.expressions = expressions;

        for (String exp : expressions) {
            String[] s = exp.split("=");
            graph.put(s[0], s[1]);
        }
    }

    public List<Long> compute(String expression) {

        String[] exp = expression.split("=");
        System.out.println(dfs(exp[1]));
        return null;
    }

    private int dfs(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }

        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }
        int i = 0;
        String exp = "";

        int result = 0;
        if (Character.isDigit(expression.charAt(0))) {
            int value = 0;
            while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                value = value * 10 + (expression.charAt(i) - '0');
                i++;
            }

            result = value;

        } else {
            while (i < expression.length() && expression.charAt(i) != '+' && expression.charAt(i) != '-') {
                exp += expression.charAt(i);
                i++;
            }
            result = dfs(graph.get(exp));

        }

        if (i < expression.length()) {
            if (expression.charAt(i) == '+') {
                result += dfs(expression.substring(i + 1));
            } else if (expression.charAt(i) == '-') {
                result -= dfs(expression.substring(i + 1));
            }
        }

        memo.put(expression, result);

        return result;
    }

    // private class ExcelNode {
    //
    //     long value;
    //     boolean isValue;
    //     String cellId;
    //     char sign = '+';
    //
    //     ExcelNode(long v) {
    //         this.value = v;
    //         isValue = true;
    //     }
    //
    //     ExcelNode(String cell, char sign) {
    //         this.cellId = cell;
    //         isValue = false;
    //         this.sign = sign;
    //     }
    // }
}
