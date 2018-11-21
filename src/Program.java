import datastructure.Tree.BinaryTree;
import leetcode.ConstructBinaryTreeFromString_536;


public class Program {

  public static void main(String[] args) {
    //
    // var l = parse("(3)(1)");
    //
    // System.out.println(l[0]);
    // System.out.println(l[1]);

    ConstructBinaryTreeFromString_536 solution = new ConstructBinaryTreeFromString_536();

    var res = solution.str2tree("51(-232)(434)");

    BinaryTree.inOrderIterative(res);

  }


  public static String[] parse(String s) {

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