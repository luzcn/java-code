package leetcode;

public class PalindromeNumber {

    public boolean isPalindrome(int x) {

        // Negative number is not palindrome
        // reverse the number maybe overflow
        // a generic solution is always check the first and last digit
        // after each checking iteratio, shrink the number by take n % div

        if (x < 0) {
            return false;
        }

        int div = 1;
        // find the largest divided number,
        // which can make x / div as a single digit
        while (x / div >= 10) {
            div *= 10;
        }

        while (x > 0) {
            // the first digit
            int first = x / div;

            // the last digit
            int last = x % 10;

            if (first != last) {
                return false;
            }

            // e.g. 234 => 3
            x = (x % div) / 10;

            // divide by 100, because the number is shrinked by 2 digits
            div /= 100;
        }
        return true;
    }
}
