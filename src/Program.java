import java.util.*;

import leetcode.FindMedianFromDataStream;

public class Program {

    public static void main(String[] args) {
        FindMedianFromDataStream fs = new FindMedianFromDataStream();

        fs.addNum(-1);
        fs.addNum(-2);


        fs.addNum(-3);
        // fs.addNum(-4);
        // fs.addNum(-3);
        System.out.println(fs.findMedian());


    }

}