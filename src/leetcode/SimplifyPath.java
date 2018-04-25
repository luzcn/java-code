package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * Corner Cases:
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 *
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        if (path.length() == 0) {
            return "";
        }
        String[] elems = path.split("/");
        Deque<String> temp = new LinkedList<>();

        for (String s : elems) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            }

            if (s.equals("..") && !temp.isEmpty()) {
                temp.removeLast();
            } else {
                temp.add("/" +s);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String s : temp) {
            result.append(s);
        }

        return result.toString();
    }
}
