package leetcode;

import java.util.*;

// In the following, every capital letter represents some hexadecimal digit from 0 to f.
//
// The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.
// For example, "#15c" is shorthand for the color "#1155cc".
//
// Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
//
// Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF,
// and has a shorthand (that is, it can be represented as some "#XYZ"
//
// Example 1:
// Input: color = "#09f166"
// Output: "#11ee66"
// Explanation:
// The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
// This is the highest among any shorthand color.
// Note:
//
// - color is a string of length 7.
// - color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
// - Any answer which has the same (highest) similarity as the best answer will be accepted.
// - All inputs and outputs should use lowercase letters, and the output is 7 characters.
public class SimilarRGBColor_800 {

    public String similarRGB(String color) {

        char[] hex = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int closest1 = -10000;
        int closest2 = -10000;
        int closest3 = -10000;

        int value1 = Integer.parseInt(color.substring(1, 3), 16);
        int value2 = Integer.parseInt(color.substring(3, 5), 16);
        int value3 = Integer.parseInt(color.substring(5), 16);

        String s1 = "", s2 = "", s3 = "";
        for (char c : hex) {
            int v1 = Integer.parseInt("" + c + c, 16);
            if (Math.abs(v1 - value1) < Math.abs(closest1)) {
                closest1 = v1 - value1;
                s1 = "" + c + c;
            }
        }

        for (char c : hex) {
            int v2 = Integer.parseInt("" + c + c, 16);
            if (Math.abs(v2 - value2) < Math.abs(closest2)) {
                closest2 = v2 - value2;
                s2 = "" + c + c;
            }
        }
        for (char c : hex) {
            int v3 = Integer.parseInt("" + c + c, 16);
            if (Math.abs(v3 - value3) < Math.abs(closest3)) {
                closest3 = v3 - value3;
                s3 = "" + c + c;
            }
        }

        return "#" + s1 + s2 + s3;
    }
}
