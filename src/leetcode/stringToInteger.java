package leetcode;

/**
 * Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases.
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 *
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
 * You are responsible to gather all the input requirements up front.
 */

public class stringToInteger {


    // need to consider the input validation and overflow problem.
    // some testcase: "    12", "111","+12340","2147483647" "-2147483648","-1230dx"
    public int myAtoi(String str) {
        str = str.trim();

        if (str.isEmpty())
            return 0;

        boolean isNegative = false;
        int i = 0;
        if (str.charAt(i) == '-') {
            isNegative = true;
            i++;
        } else if (str.charAt(i) == '+') {
            isNegative = false;
            i++;
        }

        int res = 0;
        while (i < str.length()) {

            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                break;
            }


            // if res > int_max/10, then res*10 must > int_max, so overflow
            // if res == int_max/10 but ths s[i] is larger than int_max % 10,
            //  res*10+s[i] should larger than int_max.
            if (res > Integer.MAX_VALUE / 10 ||
                    (res == Integer.MAX_VALUE / 10 && Character.getNumericValue(c) > Integer.MAX_VALUE % 10))
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            res = res * 10 + Character.getNumericValue(c);

            i++;
        }

        return isNegative ? 0 - res : res;
    }
}
