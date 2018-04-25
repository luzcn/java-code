package leetcode;

/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {

    // multiply manually, save each digit multiplication results in an array
    // then add up the results
    public String multiply(String num1, String num2) {

        if (num1.length() == 0) {
            return num2;
        }

        if (num2.length() == 0) {
            return num1;
        }

        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();
        StringBuilder res = new StringBuilder();

        int[] d = new int[n1.length() + n2.length() + 1];

        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                d[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
            }
        }

        int carry = 0;
        for (int i = 0; i < d.length; i++) {
            int value = d[i] + carry;
            d[i] = value % 10;
            carry = value / 10;

            res.append(String.valueOf(d[i]));
        }

        // reverse the result order
        res.reverse();

        // remove the front unnecessary '0'
        // the check condition is res.length > 1, because we need to return a string with at least 1 size
        // even the multiplication result is 0
        while (res.length() > 1 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }

        return res.toString();

    }


    // plus two strings
    public String plusTwoStrings(String num1, String num2) {
        // assume they are both positive

        if (num1.length() == 0) {
            return num2;
        }

        if (num2.length() == 0) {
            return num1;
        }

        // reverse the input strings
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        StringBuilder res = new StringBuilder();
        int i = 0;
        int carry = 0;
        while (i < n1.length() && i < n2.length()) {

            int sum = (n1.charAt(i) - '0') + (n2.charAt(i) - '0') + carry;
            res.append(String.valueOf(sum % 10));
            carry = sum / 10;
            i++;
        }

        while (i < n1.length()) {
            int sum = n1.charAt(i) - '0' + carry;
            res.append(String.valueOf(sum % 10));
            carry = sum / 10;
            i++;
        }

        while (i < n2.length()) {
            int sum = n2.charAt(i) - '0' + carry;
            res.append(String.valueOf(sum % 10));
            carry = sum / 10;
            i++;
        }

        if (carry > 0) {
            res.append(String.valueOf(carry));
        }

        // reverse
        return res.reverse().toString();
    }
}
