package leetcode;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check.
 *
 * Since each version is developed based on the previous version, all the versions after a bad
 * version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which
 * causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version.
 *
 * You should minimize the number of calls to the API.
 */
public class FirstBadVersion {


  // assume you already have this check function
  private boolean isBadversion(int n) {
    return true;
  }

  // binary search
  public int find(int n) {
    int left = 1;
    int right = n;

    while (left < right) {

      // (left + right)/2 may overflow
      int mid = left + (right - left) / 2;

      if (isBadversion(mid)) {
        // mid could be the first bad version
        // so need to search the range [left...mid]
        right = mid;
      } else {
        // mid is not a bad version
        // not need to check again.
        left = mid + 1;
      }
    }

    return left;
  }
}

