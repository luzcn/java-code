package leetcode;

import java.util.HashMap;

// You are playing the following Bulls and Cows game with your friend:
// - You write down a number and ask your friend to guess what the number is.
// - Each time your friend makes a guess, you provide a hint that indicates
//   how many digits in said guess match your secret number exactly in both digit and position (called "bulls")
//    and how many digits match the secret number but locate in the wrong position (called "cows").
// - Your friend will use successive guesses and hints to eventually derive the secret number.
//
// Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
//
// Please note that both secret number and friend's guess may contain duplicate digits.
//
// Example 1:
//
// Input: secret = "1807", guess = "7810"
//
// Output: "1A3B"
//
// Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
// Example 2:
//
// Input: secret = "1123", guess = "0111"
//
// Output: "1A1B"
//
// Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
// Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
public class BullsAndCows_299 {


  // use one array
  public String getHint(String secret, String guess) {
    int[] nums = new int[10];
    int bulls = 0;
    int cows = 0;

    for (int i = 0; i < secret.length(); i++) {
      int s = Character.getNumericValue(secret.charAt(i));
      int g = Character.getNumericValue(guess.charAt(i));

      if (s == g) {
        bulls++;
      } else {

        // we find a character which appears previously in guess
        if (nums[s] < 0) {
          cows++;
        }

        // find a character which appears previously in secret
        if (nums[g] > 0) {
          cows++;
        }

        nums[s]++;
        nums[g]--;
      }
    }

    return bulls + "A" + cows + "B";

  }

  // Hashmap and 2 passes, can we improve in 1 pass?
  // yes, use negative number to indicate the number has been seen in "guess"
  public String getHint2Pass(String secret, String guess) {

    HashMap<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < secret.length(); i++) {
      char c = secret.charAt(i);
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int bulls = 0;
    int cows = 0;
    boolean[] matches = new boolean[guess.length()];

    for (int i = 0; i < guess.length(); i++) {
      char c = guess.charAt(i);

      if (c == secret.charAt(i)) {
        bulls++;
        matches[i] = true;

        map.put(c, map.get(c) - 1);
        if (map.get(c) == 0) {
          map.remove(c);
        }
      }
    }

    for (int i = 0; i < guess.length(); i++) {

      if (matches[i]) {
        continue;
      }

      char c = guess.charAt(i);
      if (map.get(c) != null) {
        cows++;
        map.put(c, map.get(c) - 1);
        if (map.get(c) == 0) {
          map.remove(c);
        }
      }
    }

    return bulls + "A" + cows + "B";
  }
}
