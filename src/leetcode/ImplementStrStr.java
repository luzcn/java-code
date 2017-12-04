package leetcode;

public class ImplementStrStr {

    public int strStr(String haystack, String needle) {

        if (haystack.isEmpty() && !needle.isEmpty())
            return -1;

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            boolean isMatch = true;
            for (int j = 0; j < needle.length(); j++) {

                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) {
                return i;
            }
        }

        return -1;
    }
}
