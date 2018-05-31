package leetcode;

public class ListNode {

    public ListNode next;
    public int val;

    // build a linked list from the given array
    public static ListNode buildList(int[] nums) {
        // build a list
        // int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListNode[] nodes = new ListNode[nums.length];
        nodes[0] = new ListNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            nodes[i] = new ListNode(nums[i]);
            nodes[i - 1].next = nodes[i];
        }

        return nodes[0];
    }

    public static void print(ListNode head) {
        ListNode p = head;

        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }


    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
