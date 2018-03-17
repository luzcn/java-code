import leetcode.MergeSortedArray;

public class Program {

    public static void main(String[] args) {
        MergeSortedArray ms = new MergeSortedArray();
        int[] nums1 = new int[]{17, 23, 49, 121, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] nums2 = new int[]{1, 18, 31, 76, 90, 180};


        ms.merge(nums1, 4, nums2, 6);

        for (int n : nums1) {
            System.out.println(n);
        }
    }
}
