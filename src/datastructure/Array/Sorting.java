package datastructure.Array;

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
}
