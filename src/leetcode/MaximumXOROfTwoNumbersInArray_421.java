package leetcode;

import java.util.*;

// Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
//
// Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
//
// Could you do this in O(n) runtime?
//
// Example:
//
// Input: [3, 10, 5, 25, 2, 8]
//
// Output: 28
//
// Explanation: The maximum result is 5 ^ 25 = 28.
public class MaximumXOROfTwoNumbersInArray_421 {

    // use Trie to save the binary representation of each number
    // for each number x, to get the max value of XOR,
    // we try to find the number y from this Trie by searching the compliment of each bits of x, if cannot find the Trie node, take the bit of x

    private TrieNode root = new TrieNode();

    public int findMaximumXOR(int[] nums) {

        for (int num : nums) {
            buildTrie(num);
        }

        int res = Integer.MIN_VALUE;
        for (int x : nums) {

            TrieNode current = root;
            int currentSum = 0;
            for (int i = 31; i >= 0; i--) {
                int index = (x >> i) & 1;
                if (current.children[index ^ 1] != null) {
                    currentSum += (1 << i);
                    current = current.children[index ^ 1];
                } else {
                    current = current.children[index];
                }
            }

            res = Math.max(res,currentSum);
        }

        return res;
    }

    private void buildTrie(int num) {
        TrieNode current = root;

        for (int i = 31; i >= 0; i--) {
            int index = (num >> i) & 1;

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
    }

    private class TrieNode {
        // save the binary representation, so each node has only 2 children

        TrieNode[] children;

        TrieNode() {
            children = new TrieNode[2];
        }
    }
}
