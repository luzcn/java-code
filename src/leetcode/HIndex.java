package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a
 * function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N
 * papers have at least h citations each, and the other N − h papers have no more than h citations
 * each."
 *
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total
 * and each of them had received 3, 0, 6, 1, 5 citations respectively.
 *
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no
 * more than 3 citations each, his h-index is 3.
 *
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class HIndex {

  // From wiki, we can find the solution:
  // - sort the citations in descending order.
  // - for each i, we have "f(i) = min(citations[i], i + 1)"
  // - the max of "f(i)" is the H-Index.
  public int hIndex(int[] citations) {

    // sort the citations in descending order
    int[] sortedCitations = Arrays.stream(citations).boxed().sorted(Comparator.reverseOrder())
        .mapToInt(x -> x)
        .toArray();

    int h = 0;

    for (int i = 0; i < sortedCitations.length; i++) {
      h = Math.max(h, Math.min(sortedCitations[i], i + 1));
    }

    return h;
  }

  /**
   * The input citations array is sorted in ascending order, can we find the h-index in O(logn)
   */
  public int hInedex2(int[] citations) {

    // use binary search
    int n = citations.length;
    int l = 0, r = n - 1;

    while (l + 1 < r) {
      int mid = l + (r - l) / 2;

      if (citations[mid] == n - mid) {
        return citations[mid];
      } else if (citations[mid] < n - mid) {
        // in ascending order, so we need to check the second half array
        l = mid;
      } else {
        r = mid;
      }
    }

    if (citations[l] >= n - l) {
      return n - l;
    }

    if (citations[r] >= n - r) {
      return n - r;
    }

    return 0;
  }
}
