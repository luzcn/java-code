package careercup;

import java.util.*;

// given two non-negative number,
// calculate the sum of these two number without using "+" operator
public class Addition {

    public int plus(int a, int b) {
        if (b == 0) {
            return a;
        }

        int sum = a ^ b; // Add without include carries.
        int carry = (a & b) << 1;   // carry, but not added yet.

        return plus(sum, carry);
    }

}
