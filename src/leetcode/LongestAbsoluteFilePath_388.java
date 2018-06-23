package leetcode;

import java.util.*;


// Suppose we abstract our file system by a string in the following manner:
//
// The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
//
// dir
//     subdir1
//     subdir2
//         file.ext
// The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
//
// The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
//
// dir
//     subdir1
//         file1.ext
//         subsubdir1
//     subdir2
//         subsubdir2
//             file2.ext
// The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext
// and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
//
// We are interested in finding the longest (number of characters) absolute path to a file within our file system.
//
// For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
// and its length is 32 (not including the double quotes).
//
// Given a string representing the file system in the above format,
// return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
//
// Note:
// The name of a file contains at least a . and an extension.
// The name of a directory or sub-directory will not contain a ..
// Time complexity required: O(n) where n is the size of the input string.
//
// Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
public class LongestAbsoluteFilePath_388 {

    // use hashmap
    public int lengthLongestPath(String input) {

        // the mapping of level and file path length
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        String[] strs = input.split("\n");

        for (String s : strs) {
            // get the last index of '\t', the numbers of '\t' is the level in the map
            int level = s.lastIndexOf('\t') + 1;
            int length = s.substring(level).length();

            if (s.contains(".")) {
                // s is a file name, update the result length
                res = Math.max(res, map.getOrDefault(level, 0) + length);
            } else {
                // s is a directory name, put it into the next level (i.e. level + 1)
                // because in the final path, we need to add "/", so we need to update with length + 1;
                map.put(level + 1, map.getOrDefault(level, 0) + length + 1);
            }
        }

        return res;
    }
}
