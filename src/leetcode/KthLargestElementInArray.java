package leetcode;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the
 * sorted order, not the kth distinct element.
 *
 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {

  // selection algorithm
  // similar to quick sort
  public int findKthLargest(int[] nums, int k) {
    return this.selection(nums, k, 0, nums.length - 1);
  }

  // O(N*logK) time
  private int findKthLargestPQ(int[] nums, int k) {
    // find Kth largest, use min heap

    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int n : nums) {
      heap.offer(n);

      if (heap.size() > k) {
        heap.poll();
      }

      // if (heap.size() < k) {
      //   heap.offer(n);
      // } else {
      //   heap.poll();
      //   heap.offer(n);
      // }
    }

    return heap.peek();
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  // selection is O(n) average, but O(n^2) worst case
  private int selection(int[] nums, int k, int l, int r) {
    if (l == r) {
      return nums[l];
    }

    int m = l - 1;
    int pivot = nums[r];

    for (int i = l; i < r; i++) {
      if (nums[i] > pivot) {      // use nums[i] < pivot for kth smallest
        m++;

        // swap m and i
        swap(nums, m, i);
      }
    }

    m++;
    swap(nums, m, r);

    if (m + 1 == k) {
      return nums[m];
    } else if (m + 1 > k) {
      return selection(nums, k, l, m - 1);
    } else {
      return selection(nums, k, m + 1, r);
    }
  }

  private int selectionIterative(int[] nums, int k) {

    shuffle(nums);

    int l = 0;
    int r = nums.length - 1;

    while (l <= r) {
      int m = partition(nums, l, r);

      if (m + 1 == k) {
        return nums[m];
      } else if (m + 1 < k) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }

    return -1;
  }

  private int partition(int[] nums, int l, int r) {
    int m = l - 1;
    int pivot = nums[r];

    for (int i = l; i < r; i++) {
      if (nums[i] > pivot) {
        m++;
        swap(nums, i, m);
      }
    }

    m++;
    swap(nums, m, r);
    return m;
  }


  // randomize the input array can guarantee the selection is O(n)
  private void shuffle(int[] nums) {
    Random random = new Random();

    for (int i = nums.length - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);

      swap(nums, i, j);
    }
  }
}
