package leetcode;

import java.util.Stack;

// implement a basic calculator to evaluate a simple expression string.
//
// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
// The integer division should truncate toward zero.
public class BasicCalculator2 {

  // LL(1) parser, ues two stacks

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
        while (!stackOperator.isEmpty() && isHigherOrder(stackOperator.peek(), c)) {
          calculateNumbers();
        }
        this.stackOperator.push(c);
        i++;
      }
    }

    while (!stackOperator.isEmpty()) {
      calculateNumbers();
    }

    return stackNumber.peek();
  }
}
