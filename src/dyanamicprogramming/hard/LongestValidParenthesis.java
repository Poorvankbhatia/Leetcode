/*

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 *parentheses *substring**.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

 */
package dyanamicprogramming.hard;

import java.util.Stack;

/**
 * Created by poorvank on 19/01/17.
 */
public class LongestValidParenthesis {

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c==')') {
                if(!stack.isEmpty() && s.charAt(stack.peek())=='(') {
                    stack.pop();
                    res = Math.max(res,stack.isEmpty()?i+1:i-stack.peek());
                    continue;
                }
            }
            stack.push(i);
        }
        return res==Integer.MIN_VALUE?0:res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParenthesis().longestValidParentheses(")((())())"));

    }
}

/*

The idea is to store indexes of previous starting brackets in a stack. The first element of stack is a special
element that provides index before beginning of valid substring (base for next valid string).


1) Create an empty stack and push -1 to it. The first element
   of stack is used to provide base for next valid string.

2) Initialize result as 0.

3) If the character is '(' i.e. str[i] == '('), push index
   'i' to the stack.

2) Else (if the character is ')')
   a) Pop an item from stack (Most of the time an opening bracket)
   b) If stack is not empty, then find length of current valid
      substring by taking difference between current index and
      top of the stack. If current length is more than result,
      then update the result.
   c) If stack is empty, push current index as base for next
      valid substring.

3) Return result.

EXAMPLE:
Input: str = "(()()"

Initialize result as 0 and stack with one item -1.

For i = 0, str[0] = '(', we push 0 in stack

For i = 1, str[1] = '(', we push 1 in stack

For i = 2, str[2] = ')', currently stack has [-1, 0, 1], we pop
from the stack and the stack now is [-1, 0] and length of current
valid substring becomes 2 (we get this 2 by subtracting stack top
from current index).
Since current length is more than current result, we update result.

For i = 3, str[3] = '(', we push again, stack is [-1, 0, 3].

For i = 4, str[4] = ')', we pop from the stack, stack becomes
[-1, 0] and length of current valid substring becomes 4 (we get
this 4 by subtracting stack top from current index).
Since current length is more than current result, we update result.

 */