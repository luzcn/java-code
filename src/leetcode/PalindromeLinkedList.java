package leetcode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {

    private ListNode head;

    // recursive bottom up solution
    // similar to recursively reverse linked list
    private boolean dfs(ListNode node) {
        if (node == null) {
            return true;
        }

        boolean result = dfs(node.next);

        if (this.head.val != node.val)
            return false;

        this.head = this.head.next;
        return result;

    }


    public boolean isPalindrome(ListNode head) {
        this.head = head;

        return dfs(head);
    }

}
