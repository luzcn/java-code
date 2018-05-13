import java.util.ArrayList;

import datastructure.LinkedList.ReverseLinkedList;
import leetcode.ListNode;


public class Program {

    public static void main(String[] args) {

        var rs = new ReverseLinkedList();
        var head = ListNode.buildList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        var p = rs.reverseInRange(head, 1, 9);

        while (p != null) {
            System.out.println(p.val);

            p = p.next;
        }

    }

}