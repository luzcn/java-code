package leetcode;

import java.util.*;

// Count the number of prime numbers less than a non-negative number, n.
//
// Example:
// Input: 10
// Output: 4
// Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
public class CountPrimes {

    // Sieve of Eratosthenes
    // DP solution
    // if i is a prime number, then 2*i, 3*i, 4*i are not primes
    public int countPrimes(int n) {
        if (n <= 0) {
            return 0;
        }

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        List<Integer> result = new ArrayList<>();

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                result.add(i);
            }

            // update the dp array
            // all 2*i, 3*i,4*i....are not primes
            for (int j = 2; j * i < n; j++) {
                isPrime[j] = false;
            }
        }

        return result.size();

    }
}
