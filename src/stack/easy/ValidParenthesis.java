/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

 */
package stack.easy;

import java.util.Stack;

/**
 * Created by poorvank on 26/09/16.
 */
public class ValidParenthesis {

    public boolean isValid(String s) {

        if(s==null || s.length()==0){
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i=0;i<s.length();i++) {

            char c =s.charAt(i);
            if(c=='(' || c=='[' || c=='{') {
                stack.push(c);
            } else if(c==')') {
                if(stack.isEmpty() || stack.pop()!='(') {
                    return false;
                }
            } else if(c=='}') {
                if(stack.isEmpty() || stack.pop()!='{') {
                    return false;
                }
            } else if(c==']') {
                if(stack.isEmpty() || stack.pop()!='[') {
                    return false;
                }
            }

        }

        return stack.isEmpty();

    }

}
