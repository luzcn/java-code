import java.util.LinkedList;

import leetcode.SlidingWindowMedian_480;

public class Program {

    public static void main(String[] args) {

        SlidingWindowMedian_480 km = new SlidingWindowMedian_480();

        var res = km.medianSlidingWindow(new int[]{2147483647,1,2,3,4,5,6,7,2147483647}, 2);

        for (double s : res) {
            System.out.print(s + " ");
        }

    }

}
