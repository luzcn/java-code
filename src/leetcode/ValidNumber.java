package leetcode;

/**
 * Validate if a given string is numeric.
 * <p>
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 */
public class ValidNumber {
    public boolean isNumber(String s) {

        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }

        String regPattern = "[+-]?\\d*(\\.\\d+)?([Ee][+-]?\\d+)?";

        return java.util.regex.Pattern.matches(regPattern, s);

        // TODO: use state machine solution
    }
}
