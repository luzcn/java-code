package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction
 * in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * For example,
 *
 * Given numerator = 1, denominator = 2, return "0.5". Given numerator = 2, denominator = 1, return
 * "2". Given numerator = 2, denominator = 3, return "0.(6)".
 *
 * Thought: Negative number? INT_MIN use hashmap to save the fraction parts and the corresponding
 * index
 */
public class FractionToRecurringDecimal {


  public String fractionToDecimal(int numerator, int denominator) {

    if (numerator == 0) {
      return "0";
    }

    if (denominator == 0) {
      return "";
    }

    StringBuilder result = new StringBuilder();
    Map<Long, Integer> map = new HashMap<>();

    // only the numerator or the denominator is negative
    // we need to set the result ad negative number.
    if ((numerator < 0) ^ (denominator < 0)) {
      result.append("-");
    }

    // the abs of INT_MIN is  overflow
    long n = Math.abs(Long.valueOf(numerator));
    long d = Math.abs(Long.valueOf(denominator));

    // compute the integer part
    result.append(Long.toString(n / d));

    long r = n % d;
    if (r == 0) {
      return result.toString();
    } else {
      result.append(".");
    }

    while (r != 0) {
      if (map.containsKey(r)) {
        result.insert(map.get(r), "(");
        result.append(")");
        break;
      }

      map.put(r, result.length());
      result.append(Long.toString(r * 10 / d));
      r = r * 10 % d;
    }

    return result.toString();
  }
}
