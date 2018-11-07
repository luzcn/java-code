package leetcode;

// We have two special characters.
// - The first character can be represented by one bit 0.
// - The second character can be represented by two bits (10 or 11).
//
// Now given a string represented by several bits.
// Return whether the last character must be a one-bit character or not.
//
// The given string will always end with a zero.
//
// Example 1:
// Input:
// bits = [1, 0, 0]
// Output: True
// Explanation:
// The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
// Example 2:
// Input:
// bits = [1, 1, 1, 0]
// Output: False
// Explanation:
// The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
public class OneBitAnd2BitCharacters_717 {

  // greedy, a single 1 cannot decode to any characters
  // so, skip the 1 by
  public boolean isOneBitCharacter(int[] bits) {
    int i = 0;

    boolean res = false;
    while (i < bits.length) {
      if (bits[i] == 0) {
        res = true;
        i++;
      } else {
        res = false;
        i += 2;
      }
    }

    return res;
  }
}
