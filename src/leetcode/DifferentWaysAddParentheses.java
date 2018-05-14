package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a string of numbers and operators,
// return all possible results from computing all the different possible ways to group numbers and operators.
//
// The valid operators are +, - and *.
//
// Example 1:
//
// Input: "2-1-1"
// Output: [0, 2]
// Explanation:
// ((2-1)-1) = 0
// (2-(1-1)) = 2
// Example 2:
//
// Input: "2*3-4*5"
// Output: [-34, -14, -10, -10, 10]
// Explanation:
// (2*(3-(4*5))) = -34
// ((2*3)-(4*5)) = -14
// ((2*(3-4))*5) = -10
// (2*((3-4)*5)) = -10
// (((2*3)-4)*5) = 10
public class DifferentWaysAddParentheses {

    // divide conquer idea
    // scan the string, each time finds an operator
    // split the string into two parts and compute separately
    // Assume there is no whitespace between numbers and operators

    public List<Integer> diffWaysToCompute(String input) {

        return dfs(input);
    }


    // similar to Number of BST 2
    // for each operator, it can have L values of it's left substring and R of right substring
    // for each number in L and R, we compute them with current operator and put back to the result list
    private List<Integer> dfs(String s) {

        List<Integer> result = new ArrayList<>();
        if (s.isEmpty()) {
            return result;
        }

        int begin = 0;
        int number = 0;
        while (begin < s.length() && Character.isDigit(s.charAt(begin))) {
            number = number * 10 + Character.getNumericValue(s.charAt(begin));
            begin++;
        }

        if (begin == s.length()) {
            result.add(number);
            return result;
        }

        List<Integer> left;
        List<Integer> right;

        // i start from 0 is also fine
        // the left will be empty list
        for (int i = 1; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                continue;
            }

            left = dfs(s.substring(0, i));
            right = dfs(s.substring(i + 1));

            for (int num1 : left) {
                for (int num2 : right) {
                    result.add(compute(s.charAt(i), num1, num2));
                }
            }
        }

        return result;
    }


    private int compute(char operator, int n1, int n2) {
        switch (operator) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                break;
        }

        return 0;
    }

}
