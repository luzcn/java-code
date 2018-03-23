package leetcode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Thought:
 * - merge sort, divide conquer
 */
public class SortList {
    // assume we already have this merged two sorted list
    private MergeTwoSortedList ms = new MergeTwoSortedList();

    // divide-conquer
    // recursively split the list into two sub lists until only 1 node
    // merge these two sorted list
    public ListNode mergeSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        // count the total nodes
        int count = 0;
        ListNode p = head;
        while (p != null) {
            count++;
            p = p.next;
        }

        // Divide
        ListNode end = head;
        for (int i = 0; i < count / 2; i++) {
            end = end.next;
        }

        p = head;
        while (p.next != end) {
            p = p.next;
        }
        p.next = null;

        // conquer
        ListNode list1 = mergeSortList(head);
        ListNode list2 = mergeSortList(end);

        // merge
        return ms.merge(list1, list2);
    }


    // O(n^2) time, O(n) space
    public ListNode insertionSort(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = new ListNode(head.val);
        ListNode h = head.next;

        while (h != null) {
            ListNode newNode = new ListNode(h.val);

            if (h.val <= newHead.val) {
                // current value is less than new head value
                // need to insert as new head node
                newNode.next = newHead;
                newHead = newNode;
            } else {
                // find the correct position to insert
                // p.val < h.val <= q.val
                // or q == null
                ListNode p = newHead;
                ListNode q = p.next;

                while (q != null && q.val < h.val) {
                    p = q;
                    q = q.next;
                }

                p.next = newNode;
                newNode.next = q;
            }

            h = h.next;
        }

        return newHead;
    }
}


// int[] nums = new int[]{3, 19, 232, 31, 91, 4, 15, 23, 1230, 2, 1, 0, -1, -120};
// ListNode[] nodes = new ListNode[nums.length];
//     nodes[0] = new ListNode(nums[0]);
//             for (int i = 1; i < nums.length; i++) {
//     nodes[i] = new ListNode(nums[i]);
//     nodes[i - 1].next = nodes[i];
//     }
//
//     ListNode p = sl.mergeSortList(nodes[0]);
//     while (p != null) {
//     System.out.print(p.val + ",");
//     p = p.next;
//     }
