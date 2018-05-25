package leetcode;

import java.util.*;

// Initially, there is a Robot at position (0, 0).
// Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.
//
// The move sequence is represented by a string.
// And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down).
//
// The output should be true or false representing whether the robot makes a circle.
//
// Example 1:
// Input: "UD"
// Output: true
// Example 2:
// Input: "LL"
// Output: false
public class JudgeRouteCircle {

    // assume the given string only contains "L,R, U, D"
    public boolean judgeCircle(String moves) {
        HashMap<Character, int[]> map = new HashMap<>();
        map.put('U', new int[]{0, -1});
        map.put('D', new int[]{0, 1});
        map.put('L', new int[]{-1, 0});
        map.put('R', new int[]{1, 0});

        int x = 0, y = 0;

        for (char c : moves.toCharArray()) {
            x += map.get(c)[0];
            y += map.get(c)[1];
        }

        return x == 0 && y == 0;
    }
}
