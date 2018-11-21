package leetcode;

// You need to construct a binary tree from a string consisting of parenthesis and integers.
//
// The whole input represents a binary tree.
// It contains an integer followed by zero, one or two pairs of parenthesis.
// The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
//
// You always start to construct the left child node of the parent first if it exists.
//
// Example:
// Input: "4(2(3)(1))(6(5))"
// Output: return the tree root node representing the following tree:
//
//        4
//      /   \
//     2     6
//    / \   /
//   3   1 5
// Note:
// There will only be '(', ')', '-' and '0' ~ '9' in the input string.
// An empty tree is represented by "" instead of "()".
public class ConstructBinaryTreeFromString_536 {

  public TreeNode str2tree(String s) {

    if (s == null || s.isEmpty()) {
      return null;
    }

    return dfs(s);
  }

  private TreeNode dfs(String s) {
    if (s == null || s.isEmpty()) {
      return null;
    }

    boolean isNeg = false;
    int value = 0;
    int i = 0;
    if (s.charAt(0) == '-') {
      i = 1;
      isNeg = true;
    }

    while (i < s.length() && Character.isDigit(s.charAt(i))) {
      value = value * 10 + (s.charAt(i) - '0');
      i++;
    }

    TreeNode root = new TreeNode(isNeg ? 0 - value : value);

    String[] children = parse(s.substring(i));

    root.left = dfs(children[0]);
    root.right = dfs(children[1]);

    return root;
  }

  private String[] parse(String s) {

    int begin = 0;
    int end = 0;

    String[] res = new String[2];
    res[0] = null;
    res[1] = null;

    if (s.isBlank()) {
      return res;
    }

    int count = 0;
    String left = null;
    String right = null;
    while (end < s.length()) {

      if (s.charAt(end) == '(') {
        count++;
      } else if (s.charAt(end) == ')') {
        count--;
      }
      end++;

      if (count == 0) {
        if (left == null) {
          left = s.substring(begin + 1, end - 1);
          begin = end;
        } else {
          right = s.substring(begin + 1, end - 1);
        }
      }
    }

    res[0] = left;
    res[1] = right;

    return res;
  }
}
