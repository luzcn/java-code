package leetcode;

//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
//
//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
// But the following [1,2,2,null,3,null,3] is not:
//     1
//    / \
//   2   2
//    \   \
//    3    3
public class SymmetricTree {
    private boolean dfs(TreeNode p, TreeNode q){
        if (p == null || q == null){
            return q == null && p == null;
        }

        return (p.val == q.val) && dfs(p.left, q.right) && dfs(p.right, q.left);

    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return dfs(root.left, root.right);
    }

}
