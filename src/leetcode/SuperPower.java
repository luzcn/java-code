package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer
 * given in the form of an array.
 *
 * Example1:
 *
 * a = 2
 * b = [3]
 *
 * Result: 8
 * Example2:
 *
 * a = 2
 * b = [1,0]
 *
 * Result: 1024
 */
public class SuperPower {

    private int base = 1337;

    // compute a^k % 1337
    private int powMod(int a, int k) {
        a %= base;
        int result = 1;
        for (int i = 0; i < k; ++i) {
            result = (result * a) % base;
        }
        return result;
    }

    private int pow(int a, List<Integer> b) {
        if (b.isEmpty()) {
            return 1;
        }

        int lastDigit = b.get(b.size() - 1);
        b.remove(b.size() - 1);

        return this.powMod(this.pow(a, b), 10) * powMod(a, lastDigit) % base;

    }

    // the goal is to compute a^b%1337
    // we can observe that ab%k = (a%k)*(b%k)%k
    // so we have a^1234567%k = a^1234560*a7%k = (a^1234560%k) * (a^7%k) %k = (a^123456%k)^10 * (a%k)^7 % k
    public int superPow(int a, int[] b) {
        List<Integer> bList = new ArrayList<>();
        for (int n : b) {
            bList.add(n);
        }

        return pow(a, bList);
    }
}
