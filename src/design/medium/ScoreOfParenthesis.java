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

We count the number of layers.
If we meet '(' layers number l++
else we meet ')' layers number l--

If we meet "()", we know the number of layer outside,
so we can calculate the score res += 1 << l.
Without Stack:

public int scoreOfParentheses(String S) {
        int res = 0, l = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') l++; else l--;
            if (S.charAt(i) == ')' && S.charAt(i - 1) == '(') res += 1 << l;
        }
        return res;
    }




 */
