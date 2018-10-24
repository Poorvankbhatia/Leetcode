/*
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions )
so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.



Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4


Note:

S.length <= 1000
S only consists of '(' and ')' characters.
 */
package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank.b on 24/10/18.
 */
public class MinimumAddToMakeValid {
    public int minAddToMakeValid(String s) {
        if(s.length()==0) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c==')' && !stack.isEmpty() && stack.peek()=='(') {
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.size();
    }
}
