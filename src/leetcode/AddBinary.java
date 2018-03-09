package leetcode;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        int i = 0;
        int carry = 0;

        StringBuilder sb = new StringBuilder();

        while (i < a.length() || i < b.length()) {

            char c1 = i < a.length() ? a.charAt(i) : '0';
            char c2 = i < b.length() ? b.charAt(i) : '0';
            char r = '0';

            if (carry == 0) {
                if (c1 == '1' && c2 == '1') {
                    carry = 1;
                } else if (c1 == '1' || c2 == '1') {
                    r = '1';
                }
            } else {
                // carry is 1
                if (c1 == '1' && c2 == '1') {
                    carry = 1;
                    r = '1';
                } else if (c1 == '0' && c2 == '0') {
                    r = '1';
                    carry = 0;
                }
            }

            sb.append(r);
            i++;
        }

        if (carry > 0) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }
}
