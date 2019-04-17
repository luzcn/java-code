package leetcode;

// Given two binary strings, return their sum (also a binary string).
//
// For example, a = "11" b = "1" Return "100".
public class AddBinary {

  public String addBinary(String a, String b) {
    a = new StringBuilder(a).reverse().toString();
    b = new StringBuilder(b).reverse().toString();

    int i = 0;
    int carry = 0;

    StringBuilder sb = new StringBuilder();

    while (i < a.length() || i < b.length()) {

      int c1 = i < a.length() ? a.charAt(i) - '0' : 0;
      int c2 = i < b.length() ? b.charAt(i) - '0' : 0;

      int sum = c1 + c2 + carry;
      carry = sum >> 1;

      sb.append((sum & 1));

      // sb.append(sum % 2)
      // carry = sum / 2;

      i++;
    }

    if (carry > 0) {
      sb.append('1');
    }

    return sb.reverse().toString();
  }
}
