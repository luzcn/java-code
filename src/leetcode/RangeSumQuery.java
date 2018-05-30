package leetcode;


// Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
// Example:
// Given nums = [-2, 0, 3, -5, 2, -1]
//
// sumRange(0, 2) -> 1
// sumRange(2, 5) -> -1
// sumRange(0, 5) -> -3
// Note:
// - You may assume that the array does not change.
// - There are many calls to sumRange function.
public class RangeSumQuery {

    // 1d immutable array
    private class NumArray {

        // compute and save the total summary of the array
        private int[] totalSum;

        public NumArray(int[] nums) {
            int n = nums.length;
            totalSum = new int[n + 1];

            for (int i = 1; i < n; i++) {
                totalSum[i] = totalSum[i - 1] + nums[i - 1];
            }

        }

        // query index [2,5], need to return totalSum[6] - totalSum[2],
        // since totalSum has n + 1 size and totalSum[0] is always 0
        public int sumRange(int i, int j) {
            return totalSum[j + 1] - totalSum[i];
        }
    }

    // 1d Range Sum Query - Mutable
    // Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
    //
    // The update(i, val) function modifies nums by updating the element at index i to val.
    // Example:
    // Given nums = [1, 3, 5]
    //
    // sumRange(0, 2) -> 9
    // update(1, 2)
    // sumRange(0, 2) -> 8
    // Note:
    // - The array is only modifiable by the update function.
    // - You may assume the number of calls to update and sumRange function is distributed evenly.
    class NumArray2 {

        private SegmentTreeNode root;

        public NumArray2(int[] nums) {

            // build the seqmeng tree
            root = buildSegmentTree(nums, 0, nums.length - 1);
        }

        public void update(int i, int val) {
            this.update(i, val, root);
        }

        public int sumRange(int i, int j) {

            return this.query(i, j, root);
        }

        private SegmentTreeNode buildSegmentTree(int[] nums, int l, int r) {
            if (l > r) {
                return null;
            }

            if (l == r) {
                return new SegmentTreeNode(l, r, nums[l]);
            }

            int mid = l + (r - l) / 2;
            SegmentTreeNode leftSub = buildSegmentTree(nums, l, mid);
            SegmentTreeNode rightSub = buildSegmentTree(nums, mid + 1, r);

            SegmentTreeNode root = new SegmentTreeNode(l, r,
                    (leftSub == null ? 0 : leftSub.value) + (rightSub == null ? 0 : rightSub.value));

            root.left = leftSub;
            root.right = rightSub;

            return root;
        }

        private void update(int i, int value, SegmentTreeNode node) {
            if (node == null || i < node.start || i > node.end) {
                return;
            }

            if (i == node.start && i == node.end) {
                node.value = value;
                return;
            }

            update(i, value, node.left);
            update(i, value, node.right);

            // update the node value
            if (node.left != null) {
                node.value += node.left.value;
            }

            if (node.right != null) {
                node.value += node.right.value;
            }
        }


        private int query(int i, int j, SegmentTreeNode node) {
            if (node == null || j < node.start || i > node.end) {
                return 0;
            }

            // node  is inside the [i,j] range
            if (node.start >= i && node.end <= j) {
                return node.value;
            }

            return query(i, j, node.left) + query(i, j, node.right);
        }

        private class SegmentTreeNode {

            int start;
            int end;
            int value;

            SegmentTreeNode left;
            SegmentTreeNode right;

            SegmentTreeNode(int s, int e, int v) {
                start = s;
                end = e;
                value = v;
                left = null;
                right = null;
            }
        }
    }

    // Range Sum Query 2D - Immutable
    //Given a 2D matrix matrix,
    // find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1)
    // and lower right corner (row2, col2).

    class NumMatrix {

        private int[][] totalSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            totalSum = new int[m + 1][n + 1];

            // compute the total sum of each row
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    totalSum[i][j] = totalSum[i][j - 1] + matrix[i - 1][j - 1];
                }
            }

            // add up all the row sum for each column,
            // the result is the range sum of each rectangle
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    totalSum[i][j] += totalSum[i - 1][j];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            return totalSum[row2 + 1][col2 + 1] - totalSum[row1][col2 + 1] - totalSum[row2 + 1][col1]
                    + totalSum[row1][col2];
        }
    }

    // 308. Range Sum Query 2D - Mutable
    // Given a 2D matrix matrix,
    // find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1)
    // and lower right corner (row2, col2).
    // Note:
    // - The matrix is only modifiable by the update function.
    // - You may assume the number of calls to update and sumRegion function is distributed evenly.
    // - You may assume that row1 ≤ row2 and col1 ≤ col2.
    class NumMatrixMutable {

        SegmentTreeNode root;

        public NumMatrixMutable(int[][] matrix) {
            if (matrix.length == 0) {
                return;
            }

            root = buildSegmentTree(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
        }

        public void update(int row, int col, int val) {
            this.update(row, col, val, root);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            if (root == null) {
                return 0;
            }

            return query(row1, col1, row2, col2, root);
        }


        private SegmentTreeNode buildSegmentTree(int[][] matrix, int row1, int col1, int row2, int col2) {
            if (row1 > row2 || col1 > col2) {
                return null;
            }

            if (row1 == row2 && col1 == col2) {
                return new SegmentTreeNode(row1, col1, row2, col2, matrix[row1][col1]);
            }

            int midRow = row1 + (row2 - row1) / 2;
            int midCol = col1 + (col2 - col1) / 2;

            SegmentTreeNode node1 = buildSegmentTree(matrix, row1, col1, midRow, midCol);
            SegmentTreeNode node2 = buildSegmentTree(matrix, row1, midCol + 1, midRow, col2);
            SegmentTreeNode node3 = buildSegmentTree(matrix, midRow + 1, col1, row2, midCol);
            SegmentTreeNode node4 = buildSegmentTree(matrix, midRow + 1, midCol + 1, row2, col2);

            int value1 = node1 == null ? 0 : node1.value;
            int value2 = node2 == null ? 0 : node2.value;
            int value3 = node3 == null ? 0 : node3.value;
            int value4 = node4 == null ? 0 : node4.value;

            SegmentTreeNode root = new SegmentTreeNode(row1, col1, row2, col2, value1 + value2 + value3 + value4);
            root.children[0] = node1;
            root.children[1] = node2;
            root.children[2] = node3;
            root.children[3] = node4;

            return root;
        }

        private int query(int row1, int col1, int row2, int col2, SegmentTreeNode node) {
            if (row1 > node.endRow || row2 < node.startRow || col1 > node.endCol || col2 < node.startCol) {
                return 0;
            }

            if (node.startRow >= row1 && node.endRow <= row2 && node.startCol >= col1 && node.endCol <= col2) {
                return node.value;
            }

            int sub1 = query(row1, col1, row2, col2, node.children[0]);
            int sub2 = query(row1, col1, row2, col2, node.children[1]);
            int sub3 = query(row1, col1, row2, col2, node.children[2]);
            int sub4 = query(row1, col1, row2, col2, node.children[3]);

            return sub1 + sub2 + sub3 + sub4;
        }

        private void update(int row, int col, int val, SegmentTreeNode node) {
            if (node == null) {
                return;
            }

            if (row > node.endRow || row < node.startRow || col > node.endCol || col < node.startCol) {
                return;
            }

            if (node.startRow == row && node.endRow == row && node.startCol == col && node.endCol == col) {
                node.value = val;
                return;
            }

            update(row, col, val, node.children[0]);
            update(row, col, val, node.children[1]);
            update(row, col, val, node.children[2]);
            update(row, col, val, node.children[3]);

            node.value = (node.children[0] == null ? 0 : node.children[0].value) + (node.children[1] == null ? 0
                    : node.children[1].value) + (node.children[2] == null ? 0 : node.children[2].value) + (
                    node.children[3] == null ? 0 : node.children[3].value);
        }

        private class SegmentTreeNode {

            int startRow;
            int endRow;

            int startCol;
            int endCol;

            int value;
            SegmentTreeNode[] children;


            SegmentTreeNode(int row1, int col1, int row2, int col2, int val) {
                startRow = row1;
                startCol = col1;

                endRow = row2;
                endCol = col2;

                value = val;
                children = new SegmentTreeNode[4];
            }
        }
    }
}

