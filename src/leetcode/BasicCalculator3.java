package leetcode;

import java.util.Stack;

// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string contains only non-negative integers, +, -, *, / operators ,
// open ( and closing parentheses ) and empty spaces .
//
// The integer division should truncate toward zero.
//
// You may assume that the given expression is always valid.
// All intermediate results will be in the range of [-2147483648, 2147483647].
public class BasicCalculator3 {

  // LL(1) parser, ues two stacks
  // similar to Basic Calculator 2
  // when find ')' keep calculate and pop the stackOperator until the top is '('

  private Stack<Integer> stackNumber = new Stack<>();
  private Stack<Character> stackOperator = new Stack<>();

  // the parsing index
  private int i = 0;

  private int compute(char operator, int num1, int num2) {
    switch (operator) {
      case '+':
        return num1 + num2;
      case '-':
        return num1 - num2;
      case '*':
        return num1 * num2;
      case '/': {
        if (num2 == 0) {
          return 0;
        }

        return num1 / num2;
      }
    }

    return 0;
  }

  private boolean isHigherOrder(char operator1, char operator2) {
    if (operator1 == '*' || operator1 == '/') {
      return true;
    }

    if (operator1 == '-' && (operator2 == '+' || operator2 == '-')) {
      return true;
    }

    return false;
  }

  private void calculateNumbers() {
    int num2 = this.stackNumber.pop();
    int num1 = this.stackNumber.pop();

    char operator = this.stackOperator.pop();

    stackNumber.push(this.compute(operator, num1, num2));
  }


  private void processSign(char operator) {
    if (operator == '(') {
      this.stackOperator.push(operator);
      return;
    }

    if (operator == ')') {
      while (!stackOperator.isEmpty() && stackOperator.peek() != '(') {
        calculateNumbers();
      }

      // pop the '('
      stackOperator.pop();
    } else {

      while (!stackOperator.isEmpty() && isHigherOrder(stackOperator.peek(), operator)) {
        calculateNumbers();
      }
      this.stackOperator.push(operator);
    }
  }

  public int calculate(String s) {

    if (s == null || s.isEmpty()) {
      return 0;
    }

    while (i < s.length()) {

      char c = s.charAt(i);
      // skip white space
      if (Character.isWhitespace(c)) {
        i++;
        continue;
      }

      if (Character.isDigit(c)) {
        int number = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          number = number * 10 + Character.getNumericValue(s.charAt(i));
          i++;
        }

        stackNumber.push(number);
      } else {

        // process the sign
        this.processSign(c);
        i++;
      }
    }

    // calculate the remaining numbers in the stack
    while (!stackOperator.isEmpty()) {
      calculateNumbers();
    }

    return this.stackNumber.peek();
  }
}
