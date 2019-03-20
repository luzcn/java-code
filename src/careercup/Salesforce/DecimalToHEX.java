package careercup.Salesforce;

public class DecimalToHEX {

  public String convertDecimalToHEX(int n) {
    StringBuilder res = new StringBuilder();

    while (n > 0) {
      int temp = n % 16;

      if (temp < 10) {
        res.append((char) ('0' + temp));
      } else {
        res.append((char) ('A' + (temp - 10)));
      }

      n /= 16;
    }

    return res.reverse().toString();
  }
}
