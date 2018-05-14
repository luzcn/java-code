package leetcode;

import java.util.ArrayList;
import java.util.List;

//Given a string that contains only digits 0 - 9 and a target value,
//return all possibilities to add binary operators(not unary) + , -, or *between the digits
// so they evaluate to the target value.
//
//Examples:
//
//"123", 6 ->["1+2+3", "1*2*3"]
//"232", 8 ->["2*3+2", "2+3*2"]
//"105", 5 ->["1*0+5", "10-5"]
//"00", 0 ->["0+0", "0-0", "0*0"]
//"3456237490", 9191 ->[]
//
// https://segmentfault.com/a/1190000003797204
public class ExpressionAddOperators {
    // dfs,

    // 因为要输出所有可能的情况，必定是用深度优先搜索。
    // 问题在于如何将问题拆分成多次搜索。加减法很好处理，每当我们截出一段数字时，将之前计算的结果加上或者减去这个数，
    // 就可以将剩余的数字字符串和新的计算结果代入下一次搜索中了，直到我们的计算结果和目标一样，就完成了一次搜索。
    //
    // 然而，乘法如何处理呢？这里我们需要用一个变量记录乘法当前累乘的值，直到累乘完了，遇到下一个加号或减号再将其算入计算结果中。
    //
    // 这里有两种情况:
    //
    // 乘号之前是加号或减号，例如2+3*4，我们在2那里算出来的结果，到3的时候会加上3，计算结果变为5。
    // 在到4的时候，因为4之前我们选择的是乘号，这里3就应该和4相乘，而不是和2相加，
    // 所以在计算结果时，要将5先减去刚才加的3得到2，然后再加上3乘以4，得到2+12=14，这样14就是到4为止时的计算结果。
    //
    // 另外一种情况是乘号之前也是乘号，如果2+3*4*5，这里我们到4为止计算的结果是14了，
    // 然后我们到5的时候又是乘号，这时候我们要把刚才加的3*4给去掉，然后再加上3*4*5，也就是14-3*4+3*4*5=62。这样5的计算结果就是62。
    //
    // 因为要解决上述几种情况，我们需要这么几个变量，一个是记录上次的计算结果currRes，
    // 一个是记录上次被加或者被减的数prevNum，一个是当前准备处理的数currNum。
    //
    // 当下一轮搜索是加减法时，prevNum就是简单换成currNum，当下一轮搜索是乘法时，prevNum是prevNum乘以currNum。
    //
    // 注意
    // 第一次搜索不添加运算符，只添加数字，就不会出现+1+2这种表达式了。
    //
    // 我们截出的数字不能包含0001这种前面有0的数字，但是一个0是可以的。
    // 这里一旦截出的数字前导为0，就可以return了，因为说明前面就截的不对，从这之后都是开始为0的，后面也不可能了。

    public List<String> addOperators(String num, int target) {

        dfs(num, target, "", 0, 0);
        return this.result;

    }


    private List<String> result = new ArrayList<>();

    // value: the current expression value
    // diff: the value that has been changed in previous recursive call
    //
    // for example: 2+3, the current is 5, diff is 3
    // 2-3, the current is -1, diff is -3
    // 2+3*4, if we have 5*4, it is wrong. We should make it 5-3+3*4.
    private void dfs(String num, int target, String current, long value, long diff) {

        if (num.isEmpty()) {
            if (value == target) {
                result.add(current);
            }
            return;
        }

        for (int i = 0; i < num.length(); i++) {
            String prefix = num.substring(0, i + 1);

            if (prefix.length() > 1 && prefix.charAt(0) == '0') {
                // we don't need number like 01, 0001, but a single 0 is valid
                return;
            }

            long prevNumber = Long.parseLong(prefix);

            // "+1+2+3" the first "+" is redundant.
            if (current.isEmpty()) {
                dfs(num.substring(i), target, prefix, prevNumber, prevNumber);
            } else {
                // add + sign
                dfs(num.substring(i), target, current + "+" + prefix, value + prevNumber, prevNumber);

                // add - sign
                // the diff would be "-prefix"
                dfs(num.substring(i), target, current + "-" + prefix, value - prevNumber, -prevNumber);

                // add * sign
                // we need to first value-diff, then plus diff*prefix
                // e.g. consider expression 1+2+3*4, the current value is 1+2+3=6, the diff is 3
                // now, we need to "*4", if we use value*4 is wrong, so need to value-diff first (which would be 1+2=3),
                // then plus diff*prefix (which is 3*4). and now the new diff is diff*prefix.
                dfs(num.substring(i), target, current + "*" + prefix, (value - diff) + diff * prevNumber,
                        diff * prevNumber);
            }
        }
    }
}
