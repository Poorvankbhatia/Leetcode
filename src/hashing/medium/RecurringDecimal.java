/*

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

 */
package hashing.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 16/04/17.
 */
public class RecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {

        if(numerator==0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        if((numerator>0) ^ (denominator>0)) {
            result.append("-");
        }

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        long integerPart = num/den;
        result.append(integerPart);
        num %= den;
        if (num == 0) {
            return result.toString();
        }
        Map<Long,Integer> map = new HashMap<>();
        result.append(".");
        map.put(num,result.length());

        while (num!=0) {

            num *= 10;
            result.append(num/den);
            num %=den;

            if(map.containsKey(num)) {
                int index = map.get(num);
                result.insert(index, "(");
                result.append(")");
                break;
            } else {
                map.put(num,result.length());
            }

        }


        return result.toString();

    }

    public static void main(String[] args) {
        System.out.println(new RecurringDecimal().fractionToDecimal(0,3));
    }

}

/*

When does the fractional part repeat ?

Let us simulate the process of converting fraction to decimal. Let us look at the part where we have already figured
out the integer part which is floor(numerator/denominator). Now we are left with ( remainder = numerator%denominator ) / denominator.
If you remember the process of converting to decimal, at each step we do the following :

Multiply the remainder by 10.
Append remainder / denominator to result.
Remainder = remainder % denominator.
At any moment, if remainder becomes 0, we are done.

However, when there is a recurring sequence, remainder never becomes 0. For example if you look at 1/3, the remainder never becomes 0.

Below is one important observation :
If we start with remainder ‘rem’ and if the remainder repeats at any point of time, the digits between the two occurrence of ‘rem’ keep repeating.

 */