package datastructure.Array;

import java.util.ArrayList;
import java.util.List;

public class Sorting {

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void QuickSort(int[] nums, int l, int r) {
    if (l >= r) {
      return;
    }

    int prefix = l - 1;
    int pivot = nums[r];

    for (int i = l; i < r; i++) {
      if (nums[i] < pivot) {
        // if found any number less than pivot,
        // increase the prefix index and swap with i
        prefix++;
        swap(nums, prefix, i);
      }
    }

    // increase the prefix index and swap with the pivot value
    prefix++;
    swap(nums, prefix, r);

    this.QuickSort(nums, l, prefix - 1);
    this.QuickSort(nums, prefix + 1, r);
  }

  public void QuickSort(int[] nums) {

    this.QuickSort(nums, 0, nums.length - 1);
  }

  // merge sort

  private void merge(int[] nums, int l, int mid, int r) {

    List<Integer> temp = new ArrayList<>();

    int i = l;
    int j = mid + 1;
    while (i <= mid && j <= r) {
      if (nums[i] < nums[j]) {
        temp.add(nums[i]);
        i++;
      } else {
        temp.add(nums[j]);
        j++;
      }
    }

    while (i <= mid) {
      temp.add(nums[i]);
      i++;
    }

    while (j <= r) {
      temp.add(nums[j]);
      j++;
    }

    for (i = 0; i < temp.size(); i++) {
      nums[l + i] = temp.get(i);
    }
  }

  private void mergeSort(int[] nums, int l, int r) {
    if (l >= r) {
      return;
    }

    int mid = l + (r - l) / 2;

    // divide
    mergeSort(nums, l, mid);
    mergeSort(nums, mid + 1, r);

    // conquer
    merge(nums, l, mid, r);
  }

  public void mergeSort(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
  }
}
