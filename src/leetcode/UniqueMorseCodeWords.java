package leetcode;

import java.util.*;

// International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes,
// as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.
//
// For convenience, the full table for the 26 letters of the English alphabet is given below:
//
// [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
// Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter.
// For example, "cab" can be written as "-.-.-....-", (which is the concatenation "-.-." + "-..." + ".-").
//
// We'll call such a concatenation, the transformation of a word.
//
// Return the number of different transformations among all words we have.
//
// Example:
// Input: words = ["gin", "zen", "gig", "msg"]
// Output: 2
// Explanation:
// The transformation of each word is:
// "gin" -> "--...-."
// "zen" -> "--...-."
// "gig" -> "--...--."
// "msg" -> "--...--."
//
// There are 2 different transformations, "--...-." and "--...--.".
public class UniqueMorseCodeWords {

    // use hash set to remove duplicates

    private String[] MORSE = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
            ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        HashSet<String> uniqueCode = new HashSet<>();

        for (String w : words) {
            String code = "";

            for (char c : w.toCharArray()) {
                code += this.MORSE[c - 'a'];
            }

            uniqueCode.add(code);
        }
        return uniqueCode.size();
    }
}
