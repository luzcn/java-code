package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a start IP address ip and a number of ips we need to cover n,
// return a representation of the range as a list (of smallest possible length) of CIDR blocks.
//
// A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length.
// For example: "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.
public class IPToCIDR_751 {

  public List<String> ipToCIDR(String ip, int range) {
    long x = 0;
    String[] ips = ip.split("\\.");

    for (int i = 0; i < ips.length; ++i) {
      x = Integer.parseInt(ips[i]) + x * 256;
    }

    List<String> ans = new ArrayList<>();
    while (range > 0) {
      long step = x & -x;
      while (step > range) {
        step /= 2;
      }
      ans.add(longToIP(x, (int) step));
      x += step;

      range -= step;
    }

    return ans;
  }

  String longToIP(long x, int step) {
    int[] ans = new int[4];
    ans[0] = (int) (x & 255);
    x >>= 8;

    ans[1] = (int) (x & 255);
    x >>= 8;

    ans[2] = (int) (x & 255);
    x >>= 8;

    ans[3] = (int) x;

    int len = 33;
    while (step > 0) {
      len--;
      step /= 2;
    }
    return ans[3] + "." + ans[2] + "." + ans[1] + "." + ans[0] + "/" + len;
  }
}
