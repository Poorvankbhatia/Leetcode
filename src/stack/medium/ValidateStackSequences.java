/*

Given two sequences pushed and popped with distinct values, return true if and only
if this could have been the result of a sequence of push and pop operations on an initially empty stack.



Example 1:

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
Example 2:

Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.


Note:

0 <= pushed.length == popped.length <= 1000
0 <= pushed[i], popped[i] < 1000
pushed is a permutation of popped.
pushed and popped have distinct values.

 */
package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank.b on 29/11/18.
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int j=0;
        for(int p : pushed) {
            stack.push(p);
            while(!stack.isEmpty() && popped[j]==stack.peek()) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

}
/*

Loop through the pushed array,

Keep pushing pushed elements into stack if the top element on the stack is different from the current one of popped;
Keep poping out of the top element if it is same as the current one of popped;
Check if the stack is empty after loop.
Analysis:
Let n be pushed.length
the while loop at most run n times since the stack at most pop out n times.
Time & space: O(n).

 */