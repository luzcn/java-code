package leetcode;

import java.util.*;

// You have a lock in front of you with 4 circular wheels.
// Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
//
// The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
// Each move consists of turning one wheel one slot.
//
// The lock initially starts at '0000', a string representing the state of the 4 wheels.
//
// You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
// the wheels of the lock will stop turning and you will be unable to open it.
//
// Given a target representing the value of the wheels that will unlock the lock,
// return the minimum total number of turns required to open the lock, or -1 if it is impossible.
//
// Example 1:
// Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
// Output: 6
//
// Explanation:
// A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
// Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
// because the wheels of the lock become stuck after the display becomes the dead end "0102".
public class OpenTheLock_752 {

    public int openLock(String[] deadends, String target) {

        HashSet<String> visited = new HashSet<>();
        HashSet<String> deadSet = new HashSet<>();
        Collections.addAll(deadSet, deadends);

        HashSet<String> queue = new HashSet<>();
        HashSet<String> temp = new HashSet<>();
        queue.add("0000");
        int step = 0;

        String next1;
        String next2;

        while (!queue.isEmpty()) {

            for (String current : queue) {

                if (current.equals(target)) {
                    return step;
                }

                // the start "0000" may include in deadends
                if (visited.contains(current) || deadSet.contains(current)) {
                    continue;
                }

                visited.add(current);

                for (int i = 0; i < 4; i++) {

                    char c = current.charAt(i);
                    if (c == '0') {
                        next1 = current.substring(0, i) + '9' + current.substring(i + 1);
                        next2 = current.substring(0, i) + '1' + current.substring(i + 1);
                    } else if (c == '9') {
                        next1 = current.substring(0, i) + '8' + current.substring(i + 1);
                        next2 = current.substring(0, i) + '0' + current.substring(i + 1);
                    } else {
                        next1 = current.substring(0, i) + (char) (c - 1) + current.substring(i + 1);
                        next2 = current.substring(0, i) + (char) (c + 1) + current.substring(i + 1);
                    }

                    if (!visited.contains(next1) && !deadSet.contains(next1)) {
                        temp.add(next1);
                    }

                    if (!visited.contains(next2) && !deadSet.contains(next2)) {
                        temp.add(next2);
                    }
                }
            }

            queue = temp;
            temp = new LinkedHashSet<>();
            step++;
        }

        return -1;
    }
}
