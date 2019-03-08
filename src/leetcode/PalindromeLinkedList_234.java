package leetcode;

// Given a singly linked list, determine if it is a palindrome.
//
// Example 1:
//
// Input: 1->2
// Output: false
// Example 2:
//
// Input: 1->2->2->1
// Output: true
//
// Follow up: Could you do it in O(n) time and O(1) space?
public class PalindromeLinkedList_234 {

  private ListNode head;

  // recursive bottom up solution
  // similar to recursively reverse linked list
  private boolean dfs(ListNode node) {
    if (node == null) {
      return true;
    }

    boolean result = dfs(node.next);

    if (head.val != node.val) {
      return false;
    }

    head = head.next;
    return result;

  }


  public boolean isPalindrome(ListNode head) {
    this.head = head;

    return dfs(head);
  }

}
