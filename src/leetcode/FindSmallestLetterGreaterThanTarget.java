package leetcode;

/**
 * Given a list of sorted characters letters containing only lowercase letters, and given a target
 * letter target, find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'],
 * the answer is 'a'.
 */
public class FindSmallestLetterGreaterThanTarget {

  public char nextGreatestLetter(char[] letters, char target) {

    // linear scan
    for (char c : letters) {
      if (c > target) {
        return c;
      }
    }

    // cannot find any char greater than target
    // since letters wrapped around, return the first element
    return letters[0];
  }

  private char binarySearch(char[] letters, char target) {

    int l = 0;
    int r = letters.length - 1;

    while (l < r) {
      int mid = l + (r - l) / 2;

      if (letters[mid] <= target) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }

    return l == letters.length - 1 ? letters[0] : letters[l];
  }
}
