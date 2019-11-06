/*
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

 */
package stack.medium;

import java.util.Stack;

public class MinimumRemoveToMakeValidParenthesis {

    class Element {
        int index;
        char c;

        Element(int index, char c) {
            this.index = index;
            this.c = c;
        }
    }

    public String minRemoveToMakeValid(String s) {
        Stack<Element> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Element v = new Element(i, c);
            if (c == '(') {
                stack.push(v);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek().c == '(') {
                    stack.pop();
                } else {
                    stack.push(v);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek().index == i) {
                stack.pop();
            } else {
                sb.append(c);
            }
        }

        return sb.reverse().toString();

    }

}


/*

Another sol:

public String minRemoveToMakeValid(String s) {
    StringBuilder sb = new StringBuilder();
    int open = 0;
    for (char c : s.toCharArray()) {
        if (c == '(')
            open++;
        else if (c == ')') {
            if (open == 0) continue;
            open--;
        }
        sb.append(c);
    }

    StringBuilder result = new StringBuilder();
    for (int i = sb.length() - 1; i >= 0; i--) {
        if (sb.charAt(i) == '(' && open-- > 0) continue;
        result.append(sb.charAt(i));
    }

    return result.reverse().toString();
}

 */