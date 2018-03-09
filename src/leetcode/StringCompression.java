package leetcode;

/**
 * Given an array of characters, compress it in-place.
 *
 * The length after compression must always be smaller than or equal to the original array.
 *
 * Every element of the array should be a character (not int) of length 1.
 *
 * After you are done modifying the input array in-place, return the new length of the array.
 */
public class StringCompression {
    public int compress(char[] chars) {
        if (chars.length == 0) {
            return 0;
        }


        int begin = 0;
        int write = 0;

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 || chars[i + 1] != chars[i]) {

                // write the character
                chars[write++] = chars[begin];

                // the length/count of repeated characters
                int count = i - begin + 1;
                if (count > 1) {
                    for (char c : Integer.toString(count).toCharArray()) {
                        // write the count
                        chars[write++] = c;
                    }
                }
                begin = i + 1;
            }
        }

        return write;
    }
}
