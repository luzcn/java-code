import leetcode.FlattenAMultilevelDLL_430;
import leetcode.FlattenAMultilevelDLL_430.Node;

public class Program {

  public static void main(String[] args) {
    FlattenAMultilevelDLL_430 sol = new FlattenAMultilevelDLL_430();

    FlattenAMultilevelDLL_430.Node n1 = new Node(1);
    FlattenAMultilevelDLL_430.Node n2 = new Node(2);
    FlattenAMultilevelDLL_430.Node n3 = new Node(3);
    FlattenAMultilevelDLL_430.Node n4 = new Node(4);
    FlattenAMultilevelDLL_430.Node n5 = new Node(5);
    FlattenAMultilevelDLL_430.Node n6 = new Node(6);
    FlattenAMultilevelDLL_430.Node n7 = new Node(7);
    FlattenAMultilevelDLL_430.Node n8 = new Node(8);
    FlattenAMultilevelDLL_430.Node n9 = new Node(9);
    FlattenAMultilevelDLL_430.Node n10 = new Node(10);
    FlattenAMultilevelDLL_430.Node n11 = new Node(11);
    FlattenAMultilevelDLL_430.Node n12 = new Node(12);

    n1.next = n2;
    n2.prev = n1;
    n2.next = n3;
    n3.prev = n2;
    n3.next = n4;
    n4.prev = n3;
    n4.next = n5;
    n5.prev = n4;
    n5.next = n6;
    n6.prev = n5;


    n7.next = n8;
    n8.prev = n7;
    n8.next = n9;
    n9.prev = n8;

    n10.next = n11;
    n11.prev = n10;
    n11.next = n12;
    n12.prev = n11;


    n3.child = n7;
    // n8.child = n5;

    var res = sol.flatten(n1);

    while (res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }


}