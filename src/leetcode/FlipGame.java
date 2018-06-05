package leetcode;

import java.util.*;

public class FlipGame {

    // You are playing the following Flip Game with your friend:
    // - Given a string that contains only these two characters: + and -,
    // you and your friend take turns to flip two consecutive "++" into "--".
    //
    // The game ends when a person can no longer make a move and therefore the other person will be the winner.
    //
    // Write a function to compute all possible states of the string after one valid move.
    //
    // Example:
    //
    // Input: s = "++++"
    // Output:
    // [
    //   "--++",
    //   "+--+",
    //   "++--"
    // ]
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String flip = s.substring(0, i) + "-" + "-" + s.substring(i + 2);
                res.add(flip);
            }
        }

        return res;
    }

    // Flip Game II
    // You are playing the following Flip Game with your friend:
    // Given a string that contains only these two characters: + and -,
    // you and your friend take turns to flip two consecutive "++" into "--".
    //
    // The game ends when a person can no longer make a move and therefore the other person will be the winner.
    //
    // Write a function to determine if the starting player can guarantee a win.
    //
    // Example:
    //
    // Input: s = "++++"
    // Output: true
    // Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
    public boolean canWin(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                if (!canWin(s.substring(0, i) + "--" + s.substring(i + 2))) {
                    return true;
                }
            }
        }
        return false;
    }
}
