package leetcode;

/**
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of
 * the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * - age[B] <= 0.5 * age[A] + 7 - age[B] > age[A] - age[B] > 100 && age[A] < 100 - Otherwise, A will
 * friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend
 * request themselves.
 *
 * How many total friend requests are made?
 *
 * Example 1:
 *
 * Input: [16,16] Output: 2 Explanation: 2 people friend request each other. Example 2:
 *
 * Input: [16,17,18] Output: 2 Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 *
 * Example 3:
 *
 * Input: [20,30,100,110,120] Output: Explanation: Friend requests are made 110 -> 100, 120 -> 110,
 * 120 -> 100.
 *
 * note: 1 <= ages.length <= 20000. 1 <= ages[i] <= 120.
 */
public class FriendsOfAppropriateAges {

  // brute force O(n^2)
  public int numFriendRequestsBruteForce(int[] ages) {
    if (ages.length == 0) {
      return 0;
    }

    int n = ages.length;
    int request = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j || ages[j] <= ages[i] / 2 + 7 || ages[j] > ages[i] || (ages[j] > 100
            && ages[i] < 100)) {
          continue;
        }

        // age[B] <= 0.5 * age[A] + 7
        // age[B] > age[A]
        // age[B] > 100 && age[A] < 100

        request++;
      }
    }

    return request;
  }

  // since we know the possible ages is [1...120],
  // we can use counting sort idea,
  // - create an array with size 120, count the number of each age
  // - for each age in given array, if any age can satisfy the conditions, add up the count of corresponding age
  public int numFriendRequests(int[] ages) {

    if (ages.length == 0) {
      return 0;
    }

    int requests = 0;
    int[] count = new int[121];

    // count the age
    for (int age : ages) {
      count[age]++;
    }

    for (int ageA = 0; ageA <= 120; ageA++) {
      int countA = count[ageA];

      for (int ageB = 0; ageB <= 120; ageB++) {
        int countB = count[ageB];

        // age[B] <= 0.5 * age[A] + 7
        // age[B] > age[A]
        // age[B] > 100 && age[A] < 100
        if (ageB <= 0.5 * ageA + 7) {
          continue;
        }

        if (ageB > ageA) {
          continue;
        }

        // if (ageB > 100 && ageA < 100) {
        //     continue;
        // }

        requests += countA * countB;

        if (ageA == ageB) {
          // cannot send requests to each self
          requests -= countA;
        }
      }
    }
    return requests;
  }
}
