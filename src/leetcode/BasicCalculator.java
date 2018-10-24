package leetcode;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1" Output: 2 Example 2:
 *
 * Input: " 2-1 + 2 " Output: 3 Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)" Output: 23
 */
public class BasicCalculator {

  // Thought:
  // use LL(1) parser idea, use two stacks to save the numbers and operators
  // be careful the calculation order, if the stackOperator top is '-' need to calculate first

  // the problem has only +, - and (,)
  // - if we see a number, push it to stackNumber
  // - if the operator is (, push is to stackOperator
  // - if operator is + or -, need to check if the top of stackOperator is '-', if yes, need to calculate it first
  // - if operator is ), then need to calculate the numbers until the top of stackOperator is '(' and pop this '('

  private Stack<Character> stackOperator = new Stack<>();
  private Stack<Integer> stackNumber = new Stack<>();

  private int i;

  private int compute(char operator, int num1, int num2) {
    if (operator == '+') {
      return num1 + num2;
    }

    if (operator == '-') {
      return num1 - num2;
    }

    return 0;
  }

  private int getNumber(String s) {
    int value = 0;

    while (i < s.length() && Character.isDigit(s.charAt(i))) {
      value = value * 10 + Character.getNumericValue(s.charAt(i));
      i++;
    }

    return value;
  }


  private void calculateNumbersInStack() {
    int num2 = stackNumber.pop();
    int num1 = stackNumber.pop();

    char operator = stackOperator.pop();

    stackNumber.push(compute(operator, num1, num2));
  }

  private void processSign(char c) {
    if (c == '(') {
      stackOperator.push(c);
      return;
    }

    if (c == ')') {
      // c is ')'
      while (!stackOperator.isEmpty() && stackOperator.peek() != '(') {
        this.calculateNumbersInStack();
      }

      // pop the '('
      stackOperator.pop();

      return;
    }

    // current c is + , - sign
    // if the stackOperator top is '-' sign, need to compute the numbers in stack first
    if (!stackOperator.isEmpty() && stackOperator.peek() == '-') {
      this.calculateNumbersInStack();
    }
    stackOperator.push(c);

  }


  public int calculate(String s) {

    if (s == null || s.isEmpty()) {
      return 0;
    }
    this.i = 0;
    while (i < s.length()) {
      char c = s.charAt(i);
      if (Character.isWhitespace(c)) {
        // skip whitespace
        i++;
        continue;
      }

      if (Character.isDigit(c)) {

        // find a number, push it to stackNumber
        // and will calculate later.
        int number = this.getNumber(s);

        stackNumber.push(number);
      } else {
        this.processSign(c);
        i++;
      }
    }

    // finished parsing the input string
    // need to calculate the remaining numbers in the stack
    while (!stackOperator.isEmpty()) {
      int num2 = stackNumber.pop();
      int num1 = stackNumber.pop();

      char operator = stackOperator.pop();

      stackNumber.push(compute(operator, num1, num2));
    }

    return stackNumber.peek();
  }
}
