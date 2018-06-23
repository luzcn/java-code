package leetcode;

// Given an array of characters, compress it in-place.
//
// The length after compression must always be smaller than or equal to the original array.
//
// Every element of the array should be a character (not int) of length 1.
//
// After you are done modifying the input array in-place, return the new length of the array.
public class StringCompression {

    public int compress(char[] chars) {
        // two pointer solution
        int begin = 0;
        int end = 0;

        int n = chars.length;
        int count = 0;

        while (end < n) {
            if (chars[end] == chars[begin]) {
                count++;
                end++;
            } else {
                // move to next position, either write the count or the next distinct char
                begin++;
                if (count > 1) {
                    for (char c : String.valueOf(count).toCharArray()) {
                        chars[begin++] = c;
                    }
                }

                // copy the chars[end] to this begin position
                chars[begin] = chars[end];
                count = 0;
            }
        }

        // don't forget the last count chars
        if (count > 1) {
            for (char c : String.valueOf(count).toCharArray()) {
                chars[++begin] = c;
            }
        }

        return begin + 1;
    }
}
