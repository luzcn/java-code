import java.util.Arrays;
import leetcode.FairCandySwap_888;

public class Program {

  public static void main(String[] args) {
    FairCandySwap_888 fc = new FairCandySwap_888();

    var res = fc.fairCandySwap(new int[]{1, 1}, new int[]{2, 2});

    Arrays.stream(res).forEach(System.out::println);
  }

}