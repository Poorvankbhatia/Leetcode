/*

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
s = "100[leetcode]"


 */

package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank on 25/09/16.
 */
public class DecodeString {

    public String decodeString(String s) {
        if(s==null || s.length()==0){
            return "";
        }

        StringBuilder sb = new StringBuilder("");

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> valStack = new Stack<>();
        int value = 0;

        for (int i=0;i<s.length();i++) {

            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                value *= 10;

                value += c - '0';
            } else {
                if(c=='[') {
                    valStack.push(sb);
                    countStack.push(value);
                    sb = new StringBuilder();
                    value=0;
                }else if(c==']') {
                    StringBuilder temp = sb;
                    sb = valStack.pop();
                    int count = countStack.pop();
                    System.out.println(count);
                    while (count!=0) {
                        sb.append(temp);
                        count--;
                    }
                } else {
                    sb.append(c);
                }
            }


        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("3[a]2[bc]"));
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
    }

}
