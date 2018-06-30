package leetcode;

import java.util.*;

// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
// Note:
//
// Division between two integers should truncate toward zero.
// The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
// Example 1:
//
// Input: ["2", "1", "+", "3", "*"]
// Output: 9
// Explanation: ((2 + 1) * 3) = 9
// Example 2:
//
// Input: ["4", "13", "5", "/", "+"]
// Output: 6
// Explanation: (4 + (13 / 5)) = 6
// Example 3:
//
// Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
// Output: 22
// Explanation:
//   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
// = ((10 * (6 / (12 * -11))) + 17) + 5
// = ((10 * (6 / -132)) + 17) + 5
// = ((10 * 0) + 17) + 5
// = (0 + 17) + 5
// = 17 + 5
// = 22
public class EvaluateReversePolishNotation {

    // this is easier than basic calculator
    // because the RPN notation has already included the evaluation order
    public int evalRPN(String[] tokens) {
        // use stack
        Stack<Integer> stackNumber = new Stack<>();

        for (String s : tokens) {
            if (isOperator(s)) {
                int n2 = stackNumber.pop();
                int n1 = stackNumber.pop();

                int n = compute(n1, n2, s);

                stackNumber.push(n);

            } else {
                stackNumber.push(Integer.parseInt(s));
            }
        }
        return stackNumber.peek();
    }


    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private int compute(int number1, int number2, String op) {
        if (op.equals("+")) {
            return number1 + number2;
        }

        if (op.equals("-")) {
            return number1 - number2;
        }

        if (op.equals("*")) {
            return number1 * number2;
        }

        if (op.equals("/")) {
            if (number2 == 0) {
                return 0;
            }
            return number1 / number2;
        }

        return 0;
    }
}
