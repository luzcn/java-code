package leetcode;

// The count-and-say sequence is the sequence of integers with the first five terms as following:
//
// 1.     1
// 2.     11
// 3.     21
// 4.     1211
// 5.     111221
//
// 1 is read off as "one 1" or 11.
//
// 11 is read off as "two 1s" or 21. 21 is read off as "one 2, then one 1" or 1211.
//
// Given an integer n, generate the nth term of the count-and-say sequence.
//
// Note: Each term of the sequence of integers will be represented as a string.
//
// Example 1: Input: 1 Output: "1"
//
// Example 2: Input: 4 Output: "1211"
public class CountAndSay_38 {

  public String countAndSay(int n) {

    String number = "1";

    while (n > 1) {
      int begin = 0;
      int end = 0;
      int count = 0;

      StringBuilder sb = new StringBuilder();

      while (end < number.length()) {
        if (number.charAt(begin) == number.charAt(end)) {
          count++;
          end++;
        } else {
          sb.append(count).append(number.charAt(begin));
          begin = end;
          count = 0;
        }

      }
      sb.append(count).append(number.charAt(begin));
      number = sb.toString();
      n--;
    }

    return number;
  }

  // public String countAndSay2(int n) {
  //
  //   String number = "1";
  //
  //   while (n-- > 1) {
  //
  //     StringBuilder sb = new StringBuilder();
  //
  //     int count = 1;
  //     for (int i = 0; i < number.length(); i++) {
  //       if (i == number.length() - 1 || number.charAt(i) != number.charAt(i + 1)) {
  //         sb.append(count).append(number.charAt(i));
  //         count = 0;
  //       }
  //       count++;
  //     }
  //
  //     number = sb.toString();
  //   }
  //
  //   return number;
  // }
}
