import java.util.Arrays;
import java.util.List;

import leetcode.LongestWordInDictionaryThroughDeleting;

public class Program {

    public static void main(String[] args) {
        LongestWordInDictionaryThroughDeleting lw = new LongestWordInDictionaryThroughDeleting();
        List<String> words = Arrays.asList("ba", "ab", "a", "b");

        System.out.println(lw.findLongestWord("bab", words));

    }
}