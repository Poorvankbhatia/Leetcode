/*

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.



Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".


Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

 */
package stack.easy;

import java.util.Stack;

/**
 * Created by poorvank.b on 03/06/18.
 */
public class BackSpaceStringCompare {

    public boolean backspaceCompare(String S, String T) {

        return getFinalString(S).equals(getFinalString(T));

    }

    private String getFinalString(String s) {
        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            if(c!='#') {
                stack.push(c);
            } else {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();

    }

}
