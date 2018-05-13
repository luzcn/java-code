package leetcode;

import java.util.List;

// Given a data stream input of non-negative integers a1, a2, ..., an, ...,
// summarize the numbers seen so far as a list of disjoint intervals.
//
// For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
//
// [1, 1]
// [1, 1], [3, 3]
// [1, 1], [3, 3], [7, 7]
// [1, 3], [7, 7]
// [1, 3], [6, 7]
// Follow up:
// What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
public class DataStreamAsDisjointIntervals {

    private BinarySearchTreeNode root;

    /**
     * Initialize your data structure here.
     */
    public DataStreamAsDisjointIntervals() {

    }

    public void addNum(int val) {
        root = root.insert(root, val);
    }

    public List<Interval> getIntervals() {

        return null;
    }


    private class BinarySearchTreeNode {

        BinarySearchTreeNode left;
        BinarySearchTreeNode right;
        BinarySearchTreeNode parent;
        int val;

        BinarySearchTreeNode(int v) {
            this.val = v;
        }

        public BinarySearchTreeNode insert(BinarySearchTreeNode root, int val) {
            if (root == null) {
                return new BinarySearchTreeNode(val);
            }

            if (val < root.val) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }

            return root;
        }
    }

    private class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
