package leetcode;

public class MaximumProductOfWordLengths {

    // brute force + bit manipulate
    // the question only uses lower case character, so we can use an integer to represent it
    // e.g. 'a' => 0 , "ab" => binary '10'
    // use bits mask to represent a word string and save the bits in an array
    public int maxProduct(String[] words) {

        int maxLengthProduct = 0;

        if (words == null || words.length == 0) {
            return 0;
        }

        // the array to save each word string integer representation
        int[] mask = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {

                mask[i] |= 1 << (c - 'a');
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    // no common chars
                    maxLengthProduct = Math.max(maxLengthProduct, words[i].length() * words[j].length());
                }
            }
        }

        return maxLengthProduct;
    }

}
