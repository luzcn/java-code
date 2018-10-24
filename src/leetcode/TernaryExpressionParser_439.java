package leetcode;

import java.util.Stack;

// Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression.
// You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).
//
// Note:
//
// The length of the given string is ≤ 10000.
// Each number will contain only one digit.
// The conditional expressions group right-to-left (as usual in most languages).
// The condition will always be either T or F. That is, the condition will never be a digit.
// The result of the expression will always evaluate to either a digit 0-9, T or F.
// Example 1:
//
// Input: "T?2:3"
//
// Output: "2"
//
// Explanation: If true, then result is 2; otherwise result is 3.
// Example 2:
//
// Input: "F?1:T?4:5"
//
// Output: "4"
//
// Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//              "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
//           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
//           -> "4"                                    -> "4"
public class TernaryExpressionParser_439 {

  // 从右边开始找到第一个问号，然后先处理这个三元表达式，然后再一步一步向左推，这也符合程序是从右向左执行的特点
  // use a stack save the position of expression "?", the problem states each number has only 1 digit and each condition is T/F
  // we can see each valid Ternary expression or sub-expression has length 5
  public String parseTernary(String expression) {

    // save the index of "?"
    Stack<Integer> stack = new Stack<>();
    String res = expression;

    for (int i = 0; i < expression.length(); i++) {
      if (expression.charAt(i) == '?') {
        stack.push(i);
      }
    }

    while (!stack.isEmpty()) {
      int pos = stack.pop();

      res = res.substring(0, pos - 1) + evaluate(res.substring(pos - 1, pos + 4)) + res
          .substring(pos + 4);
    }

    return res;
  }

  private String evaluate(String expr) {
    if (expr.charAt(0) == 'T') {
      return expr.substring(2, 3);
    } else {
      return expr.substring(4, 5);
    }
  }

  public String parseTernary2(String expression) {

    // save the character
    Stack<Character> stack = new Stack<>();
    // String res = expression;

    for (int i = expression.length() - 1; i >= 0; i--) {
      char c = expression.charAt(i);

      if (!stack.isEmpty() && stack.peek() == '?') {
        stack.pop(); // remove ?
        char first = stack.pop();

        stack.pop(); // remove :

        char second = stack.pop();
        //
        // if (c == 'T') {
        //     stack.push(first);
        // } else {
        //     stack.push(second);
        // }

        stack.push(c == 'T' ? first : second);
      } else {
        stack.push(c);
      }
    }
    return String.valueOf(stack.peek());
  }

  // all we need is evaluating from the last "?", so use string.lastIndexOf function, evaluate the ternary express and add back
  public String parseTernary3(String expression) {
    String res = expression;

    while (res.lastIndexOf('?') > 0) {
      int pos = res.lastIndexOf('?');

      res = res.substring(0, pos - 1) + evaluate(res.substring(pos - 1, pos + 4)) + res
          .substring(pos + 4);
    }

    return res;
  }

}
