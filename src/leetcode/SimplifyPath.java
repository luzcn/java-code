package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/////
// Given an absolute path for a file (Unix-style), simplify it.
//
// For example,
// path = "/home/", => "/home"
// path = "/a/./b/../../c/", => "/c"
//
// Corner Cases:
//
// Did you consider the case where path = "/../"?
// In this case, you should return "/".
//
// Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
// In this case, you should ignore redundant slashes and return "/home/foo".
///
public class SimplifyPath {

    public String simplifyPath(String path) {
        if (path.length() == 0) {
            return "";
        }
        String[] dirs = path.split("/");
        LinkedList<String> temp = new LinkedList<>();

        for (String s : dirs) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            }

            if (s.equals("..")) {
                if (!temp.isEmpty()) {
                    temp.removeLast();
                }
            } else {
                temp.addLast("/" + s);
            }
        }

        if (temp.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        for (String s : temp) {
            result.append(s);
        }

        return result.toString();
    }
}
