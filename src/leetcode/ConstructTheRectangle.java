package leetcode;

import java.util.*;

// 1. The area of the rectangular web page you designed must equal to the given target area.
//
// 2. The width W should not be larger than the length L, which means L >= W.
//
// 3. The difference between length L and width W should be as small as possible.
// You need to output the length L and the width W of the web page you designed in sequence.
// Example:
// Input: 4
// Output: [2, 2]
// Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1].
// But according to requirement 2, [1,4] is illegal; according to requirement 3,
// [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
public class ConstructTheRectangle {
    public int[] constructRectangle(int area) {
        int width = (int) Math.sqrt(area);

        while(area % width != 0){
            width--;
        }

        return new int[]{area/width, width};
    }
}
