import leetcode.NextGreaterElement_503_556;

public class Program {

    public static void main(String[] args) {

        NextGreaterElement_503_556 ns = new NextGreaterElement_503_556();

        var s = ns.nextGreaterElements(new int[]{1, 2, 3, 2, 1});

        for (int n : s) {
            System.out.print(n + " ");
        }
    }


}
