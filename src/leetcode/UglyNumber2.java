package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 */
public class UglyNumber2 {

    public int nthUglyNumber(int n) {

        int i = 0, j = 0, k = 0;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);

        while (numbers.size() < n) {
            int value = Math.min(numbers.get(i) * 2, Math.min(numbers.get(j) * 3, numbers.get(k) * 5));

            if (value == numbers.get(i) * 2) {
                i++;
            }

            if (value == numbers.get(j) * 3) {
                j++;
            }

            if (value == numbers.get(k) * 5) {
                k++;
            }

            numbers.add(value);
        }

        return numbers.get(numbers.size() - 1);
    }
}
