/*

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

 */
package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank on 19/02/17.
 */
public class BuildLowestNumber {

    public String removeKdigits(String num, int k) {

        if(null==num || k>=num.length()) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        int n = num.length();
        int i = 0;
        while(i<n){
            //whenever a digit is greater than the next digit, remove it
            while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }

        while (k>0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }



        return sb.length()!=1?removeLeadingZeros(sb.reverse().toString()):sb.toString();

    }

    private String removeLeadingZeros(String str) {
        while (str.indexOf("0")==0 && str.length()>1)
            str = str.substring(1);
        return str;
    }

    public static void main(String[] args) {
        String s = "1111111";
        int n =3;
        System.out.println(new BuildLowestNumber().removeKdigits(s,n));
    }

}
