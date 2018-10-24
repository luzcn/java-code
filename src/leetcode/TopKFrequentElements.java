package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/////
// Given a non-empty array of integers, return the k most frequent elements.
//
// For example,
// Given [1,1,1,2,2,3] and k = 2, return [1,2].
//
// Note:
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
// Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
///
public class TopKFrequentElements {

  public List<Integer> topKFrequent(int[] nums, int k) {
    List<Integer> result = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(map::get));

    for (int n : nums) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }

    // for (int n : map.keySet()) {
    //     minHeap.offer(n);
    //
    //     if (minHeap.size() > k) {
    //         minHeap.poll();
    //     }
    // }

    minHeap.addAll(map.keySet());
    while (minHeap.size() > k) {
      minHeap.poll();
    }

    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll());
    }

    Collections.reverse(result);

    return result;
  }

  // bucket sort, we know the frequency should be nums.length as maximum
  // so create n bucket,
  public List<Integer> topKFrequentBucketSort(int[] nums, int k) {
    List<List<Integer>> bucket = new ArrayList<>();
    for (int num : nums) {
      bucket.add(new ArrayList<>());
    }

    // List<Integer>[] bucket = new List[nums.length + 1];
    HashMap<Integer, Integer> mapFrequency = new HashMap<>();

    for (int n : nums) {
      mapFrequency.put(n, mapFrequency.getOrDefault(n, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : mapFrequency.entrySet()) {
      int element = entry.getKey();
      int frequency = entry.getValue();

      bucket.get(frequency).add(element);
    }

    List<Integer> res = new ArrayList<>();
    // int count = 0;
    for (int i = bucket.size() - 1; i >= 0 && res.size() < k; i--) {

      if (bucket.get(i) != null) {
        res.addAll(bucket.get(i));
      }
    }

    return res;
  }
}
