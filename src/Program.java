import java.util.ArrayList;
import java.util.List;

import leetcode.ListNode;
import leetcode.SwapNodesInPairs;

public class Program {

    public static void main(String[] args) {
        SwapNodesInPairs sw = new SwapNodesInPairs();

        int[] nums = new int[]{1, 2, 3, 4};

        List<ListNode> nodes = new ArrayList<>();

        nodes.add(new ListNode(nums[0]));

        for (int i = 1; i < nums.length; i++) {
            ListNode newNode = new ListNode(nums[i]);

            nodes.get(i - 1).next = newNode;

            nodes.add(newNode);
        }

        ListNode p = sw.swapPairs(nodes.get(0));

        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}