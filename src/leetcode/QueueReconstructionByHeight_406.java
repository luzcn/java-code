package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Suppose you have a random list of people standing in a queue.
//
// Each person is described by a pair of integers (h, k),
// where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
//
// Write an algorithm to reconstruct the queue.
//
// Note:
// The number of people is less than 1,100.
//
//
// Example
//
// Input:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
// Output:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
public class QueueReconstructionByHeight_406 {

  public int[][] reconstructQueue(int[][] people) {
    int m = people.length;

    // Insert a short person in front of tall persons will not affect the relative order of the taller persons,
    // thus sort from higher height to lower height
    // but, int Java, Arrays.sort, doesn't support customized sorting, so convert
    List<int[]> res = new ArrayList<>();

    List<int[]> peopleList = new ArrayList<>(Arrays.asList(people));

    peopleList.sort((x, y) -> {
      if (x[0] == y[0]) {
        return x[1] - y[1];
      }

      return y[0] - x[0];
    });

    for (int[] p : peopleList) {

      // in java ArrayList, add with index, if index == list size will append at the end of the list
      res.add(p[1], p);
    }

    // int[][] ans = new int[people.length][2];
    // for (int i = 0; i < people.length; i++) {
    //     ans[i] = new int[]{res.get(i)[0], res.get(i)[1]};
    // }

    return res.toArray(new int[people.length][2]);
  }
}
