package leetcode;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to
 * be less than 231 - 1.
 *
 * For example, 123 -> "One Hundred Twenty Three" 12345 -> "Twelve Thousand Three Hundred Forty
 * Five" 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class integerToEnglishWords {

  private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
      "Eight", "Nine",
      "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
      "Eighteen",
      "Nineteen"};

  private final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
      "Eighty",
      "Ninety"};
  private final String[] THOUSANDS = {"Thousand", "Million", "Billion"};


  // help to process the number less than hundred
  private String convertHundred(int num) {
    if (num == 0) {
      return "";
    } else if (num < 20) {
      return LESS_THAN_20[num] + " ";
    } else if (num < 100) {
      return TENS[num / 10] + " " + convertHundred(num % 10);
    } else {
      return LESS_THAN_20[num / 100] + " Hundred " + convertHundred(num % 100);
    }
  }

  public String numberToWords(int num) {

    if (num == 0) {
      return "Zero";
    }

    // first process the hundreds
    String res = convertHundred(num % 1000);

    for (int i = 0; i < 3; i++) {
      num /= 1000;

      if (num % 1000 > 0) {
        // if == 0, we can skip thousand or million
        res = convertHundred(num % 1000) + THOUSANDS[i] + " " + res;
      }
    }

    return res;
  }

}
