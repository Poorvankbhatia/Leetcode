/*

Given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any bracket.





Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Example 4:

Input: s = "a(bcdefghijkl(mno)p)q"
Output: "apmnolkjihgfedcbq"


Constraints:

0 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It's guaranteed that all parentheses are balanced.

 */
package string.medium;

import java.util.LinkedList;
import java.util.Stack;

public class ReverseStringBetweenParenthesis {

    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c==')') {
                LinkedList<Character> list = new LinkedList<>();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    list.addLast(stack.pop());
                }
                stack.pop();
                while (list.size() > 0) {
                    stack.push(list.removeFirst());
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseStringBetweenParenthesis().reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

}

/*

Recursive sol:

public String reverseParentheses(String s) {
        if(s.length() == 0) return "";

        int begin = 0, end = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') begin = i;
            if(s.charAt(i) == ')') {
                end = i;
                StringBuilder sb = new StringBuilder(s.substring(begin+1, end));
                return reverseParentheses(s.substring(0, begin) + sb.reverse().toString() + s.substring(end+1));
            }
        }
        return s;
    }

 */