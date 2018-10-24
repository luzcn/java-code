package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
// The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
//
// Reconstruction means building a shortest common supersequence of the sequences in seqs
// (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
//
// Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
// Example 1:
// Input:
// org: [1,2,3], seqs: [[1,2],[1,3]]
//
// Output:
// false
// Explanation:
// [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
//
// Example 2:
// Input:
// org: [1,2,3], seqs: [[1,2]]
// Output:
// false
// Explanation:
// The reconstructed sequence can only be [1,2].
//
// Example 3:
// Input:
// org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
// Output:
// true
// Explanation:
// The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
//
// Example 4:
// Input:
// org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
// Output:
// true
public class SequenceReconstruction {

  // BFS topo sort, get the sequence
  // but how to check the only one sequence?
  // - After constructed the inDegree map, if we found there are more than 1 node has 0 indegree,
  // it means there are more than 1 super-sequence, we can return false
  // - we constructing the sequence, we need to check if each character are equivalent to the given org
  // - after finished the reconstruction, check if the super-sequence has the same length of given org.

  public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

    HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    HashMap<Integer, Integer> inDegree = new HashMap<>();

    // build graph
    for (List<Integer> seq : seqs) {
      if (seq.size() == 1) {
        if (!graph.containsKey(seq.get(0))) {
          graph.put(seq.get(0), new HashSet<>());
          inDegree.put(seq.get(0), 0);
        }
        continue;
      }

      for (int i = 0; i < seq.size() - 1; i++) {

        int u = seq.get(i);
        int v = seq.get(i + 1);

        // first need to initialize the indegree with all the u and v
        if (!graph.containsKey(u)) {
          graph.put(u, new HashSet<>());
          inDegree.put(u, 0);
        }

        if (!graph.containsKey(v)) {
          graph.put(v, new HashSet<>());
          inDegree.put(v, 0);
        }

        // update the indegree of v node
        if (graph.get(u).add(v)) {
          inDegree.put(v, inDegree.get(v) + 1);
        }
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
      if (entry.getValue() == 0) {
        queue.add(entry.getKey());
      }
    }

    int index = 0;
    while (!queue.isEmpty()) {

      if (queue.size() > 1) {
        // there are more than 1 reconstruction sequence
        return false;
      }
      int current = queue.poll();

      if (index == org.length || current != org[index]) {
        return false;
      }
      index++;

      for (int child : graph.get(current)) {

        inDegree.put(child, inDegree.get(child) - 1);

        if (inDegree.get(child) == 0) {
          queue.offer(child);
        }
      }
    }

    // ensure the reconstructed sequence is the super sequence
    // and it has the same length of the given org
    return index == org.length && index == graph.size();
  }
}
