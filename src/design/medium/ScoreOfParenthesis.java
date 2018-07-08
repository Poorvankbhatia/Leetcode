/*

Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6


Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50


 */
package design.medium;

import java.util.Stack;

/**
 * Created by poorvank.b on 08/07/18.
 */
public class ScoreOfParenthesis {

    public int scoreOfParentheses(String S) {

        if(S==null || S.length()==0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if(c=='(') {
                stack.push(-1);
            } else {
                int sum=0;
                int x;
                while ((x=stack.pop())!=-1) {
                    sum+=x;
                }
                stack.push(sum==0?1:(2*sum));
            }
        }

        int result = 0;

        while (!stack.isEmpty()) {
            result+=stack.pop();
        }

        return result;

    }

}

/*

O(n)

Another solution:
Every position in the string has a depth - some number of matching parentheses surrounding it. For example, the dot in (()(.())) has depth 2, because of these parentheses: (__(.__))

Our goal is to maintain the score at the current depth we are on. When we see an opening bracket, we increase our depth, and our score at the new depth is 0. When we see a closing bracket, we add twice the score of the previous deeper part - except when counting (), which has a score of 1.

For example, when counting (()(())), our stack will look like this:

[0, 0] after parsing (
[0, 0, 0] after (
[0, 1] after )
[0, 1, 0] after (
[0, 1, 0, 0] after (
[0, 1, 1] after )
[0, 3] after )
[6] after )

 Stack<Integer> stack = new Stack();
    stack.push(0); // The score of the current frame

    for (char c: S.toCharArray()) {
        if (c == '(')
            stack.push(0);
        else {
            int v = stack.pop();
            int w = stack.pop();
            stack.push(w + Math.max(2 * v, 1));
        }
    }

    return stack.pop();




 */
