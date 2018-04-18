/*

Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or *
between the digits so they evaluate to the target value.

Examples:
"123", 6 -> ["1+2+3", "1*2*3"]
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []

 */
package string.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 18/04/18.
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();

        util(num,result,"",0,0L,target,0L);

        return result;
    }

    private void util(String num,List<String> result,String currentExpression,int pos,Long currentVal,int target,Long last) {

        if(pos==num.length()) {
            if(currentVal==target) {
                result.add(currentExpression);
            }
            return;
        }

        for (int i=pos;i<num.length();i++) {

            if(i!=pos && num.charAt(pos)=='0') {
                break;
            }

            Long val = Long.parseLong(num.substring(pos,i+1));

            if(pos==0) {

                util(num,result,currentExpression+val,i+1,val,target,val);

            } else {

                util(num,result,currentExpression+"+"+val,i+1,currentVal+val,target,val);
                util(num,result,currentExpression+"-"+val,i+1,currentVal-val,target,-val);
                util(num,result,currentExpression+"*"+val,i+1,currentVal-last+last*val,target,last*val);

            }

        }

    }

    public static void main(String[] args) {
        System.out.println(new ExpressionAddOperators().addOperators("105",5));
    }

}

/*

This problem can be solved by putting all possible binary operator in mid between to digits and evaluating them and then check they evaluate to target or not.

While writing the recursive code, we need to keep these variable as argument of recursive method – result vector, input string, current expression string,
 target value, position till which input is processed, current evaluated value and last value in evaluation.
Last value is kept in recursion because of multiplication operation, while doing multiplication we need last value for correct evaluation.
See below example for better understanding –

Input is 125, suppose we have reached till 1+2 now,
Input = “125”, current expression = “1+2”,
position = 2, current val = 3, last = 2

Now when we go for multiplication, we need last
value for evaluation as follows:

current val = current val - last + last * current val

First we subtract last and then add last * current
val for evaluation, new last is last * current val.
current val = 3 – 2 + 2*5 = 11
last = 2*5 = 10
Another thing to note in below code is, we have ignored all numbers which start from 0 by imposing a condition as first condition inside the loop so that
we will not process number like 03, 05 etc.

 */