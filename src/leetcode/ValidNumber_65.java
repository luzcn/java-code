package leetcode;

// Validate if a given string is numeric.
// Some examples:
// "0" => true
// " 0.1 " => true
// "abc" => false
// "1 a" => false
// "2e10" => true

// string s1 = "0"; // True
// string s2 = " 0.1 "; // True
// string s3 = "abc"; // False
// string s4 = "1 a"; // False
// string s5 = "2e10"; // True
//
// string s6 = "-e10"; // False
// string s7 = " 2e-9 "; // True
// string s8 = "+e1"; // False
// string s9 = "1+e"; // False
// string s10 = " "; // False
//
// string s11 = "e9"; // False
// string s12 = "4e+"; // False
// string s13 = " -."; // False
// string s14 = "+.8"; // True
// string s15 = " 005047e+6"; // True
//
// string s16 = ".e1"; // False
// string s17 = "3.e"; // False
// string s18 = "3.e1"; // True
// string s19 = "+1.e+5"; // True
// string s20 = " -54.53061"; // True
//
// string s21 = ". 1"; // False

//我们所需要关注的除了数字以外的特殊字符有空格 ‘ ’， 小数点 '.', 自然数 'e/E', 还要加上正负号 '+/-"，
// 除了这些字符需要考虑意外，出现了任何其他的字符，可以马上判定不是数字。下面我们来一一分析这些出现了也可能是数字的特殊字符：
//
// 1. 空格 ‘ ’： 空格分为两种情况需要考虑，一种是出现在开头和末尾的空格，一种是出现在中间的字符。出现在开头和末尾的空格不影响数字，
// 而一旦中间出现了空格，则立马不是数字。解决方法：预处理时去掉字符的首位空格，中间再检测到空格，则判定不是数字。
//
// 2. 小数点 '.'：小数点需要分的情况较多，首先的是小数点只能出现一次，但是小数点可以出现在任何位置，开头(".3"), 中间("1.e2"), 以及结尾("1." ),
// 而且需要注意的是，小数点不能出现在自然数 'e/E' 之后，如 "1e.1" false, "1e1.1" false。
// 还有，当小数点位于末尾时，前面必须是数字，如 "1."  true，" -." false。解决方法：开头中间结尾三个位置分开讨论情况。
//
// 3. 自然数 'e/E'：自然数的前后必须有数字，即自然数不能出现在开头和结尾，如 "e" false,  ".e1" false, "3.e" false, "3.e1" true。
// 而且小数点只能出现在自然数之前，还有就是自然数前面不能是符号，如 "+e1" false, "1+e" false. 解决方法：开头中间结尾三个位置分开讨论情况。
//
// 4. 正负号 '+/-"，正负号可以再开头出现，可以再自然数e之后出现，但不能是最后一个字符，后面得有数字，
// 如  "+1.e+5" true。解决方法：开头中间结尾三个位置分开讨论情况。

// 1. 在讨论三个位置之前做预处理，去掉字符串首尾的空格，可以采用两个指针分别指向开头和结尾，遇到空格则跳过，分别指向开头结尾非空格的字符。
//
// 2. 对首字符处理，首字符只能为数字或者正负号 '+/-"，我们需要定义三个flag在标示我们是否之前检测到过小数点，自然数和正负号。
// 首字符如为数字或正负号，则标记对应的flag，若不是，直接返回false。
//
// 3. 对中间字符的处理，中间字符会出现五种情况，数字，小数点，自然数，正负号和其他字符。
//
// - 若是数字，标记flag并通过。
//
// - 若是自然数，则必须是第一次出现自然数，并且前一个字符不能是正负号，而且之前一定要出现过数字，才能标记flag通过。
//
// - 若是正负号，则之前的字符必须是自然数e，才能标记flag通过。
//
// - 若是小数点，则必须是第一次出现小数点并且自然数没有出现过，才能标记flag通过。
//
// - 若是其他，返回false。
//
// 4. 对尾字符处理，最后一个字符只能是数字或小数点，其他字符都返回false。
//
// - 若是数字，返回true。
// - 若是小数点，则必须是第一次出现小数点并且自然数没有出现过，还有前面必须是数字，才能返回true。
public class ValidNumber_65 {

    public boolean isNumber(String s) {

        // remove unnecessary space
        s = s.trim();

        int n = s.length();
        if (n == 0) {
            return false;
        }

        boolean eExisted = false;
        boolean digitExisted = false;
        boolean dotExisted = false;

        // process the first character
        if (Character.isDigit(s.charAt(0))) {
            digitExisted = true;
        } else if (s.charAt(0) == '.') {
            dotExisted = true;
        } else if (s.charAt(0) != '+' && s.charAt(0) != '-') {
            return false;
        }

        for (int i = 1; i < n - 1; i++) {

            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // character is a digit
                digitExisted = true;

            } else if (c == 'e' || c == 'E') {

                // e/E can only be followed after a digit, and only appear once
                if (!eExisted && digitExisted && s.charAt(i - 1) != '+' && s.charAt(i - 1) != '-') {
                    eExisted = true;
                } else {
                    return false;
                }

            } else if (c == '+' || c == '-') {
                // +, - can only follows e/E
                if (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                // dot can only occur once and cannot occur after e/E
                if (dotExisted || eExisted) {
                    return false;
                }

                dotExisted = true;
            } else {
                return false;
            }
        }

        // Process the last char,
        // it can only be digit or dot,
        // when it is dot, there should be no dot and e/E before and must follow a digit

        if (s.charAt(n - 1) == '.') {
            if (dotExisted || eExisted || !digitExisted) {
                return false;
            }
            dotExisted = true;
        } else if (!Character.isDigit(s.charAt(n - 1))) {
            return false;
        }

        return true;
    }
}
