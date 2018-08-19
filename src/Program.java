import java.util.*;


public class Program {

    public static void main(String[] args) {
        List<String> res = removeInvalid("()())()");

        System.out.println(res);
    }

    // Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

    public static List<String> removeInvalid(String s) {
        // bfs

        List<String> queue = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> res = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();

        queue.add(s);

        while (!queue.isEmpty()) {

            for (String current : queue) {

                if (visited.contains(current)) {
                    continue;
                }

                visited.add(current);

                if (isValid(current)) {
                    if (res.isEmpty() || res.get(res.size() - 1).length() == current.length()) {
                        res.add(current);
                    }
                    continue;
                }

                for (int i = 0; i < current.length(); i++) {
                    char c = current.charAt(i);
                    if (c == '(' || c == ')') {
                        String next = current.substring(0, i) + current.substring(i + 1);

                        temp.add(next);
                    }
                }
            }

            queue = temp;
            temp = new ArrayList<>();
        }

        return res;
    }


    private static boolean isValid(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }

        }

        return count == 0;
    }
}
