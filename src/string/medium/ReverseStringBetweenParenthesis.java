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

public class ReverseStringBetweenParenthesis {

    public String reverseParentheses(String s) {
        int begin = 0;
        int end;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(')
                begin = i;
            if(s.charAt(i) == ')'){
                end = i;
                String temp = s.substring(begin + 1, end);
                return reverseParentheses(s.substring(0, begin) + reverseString(temp) + s.substring(end + 1));
            }
        }
        return s;
    }

    private String reverseString(String s){
        char[] temp = s.toCharArray();
        StringBuilder r = new StringBuilder();
        for (int i = temp.length-1; i>=0; i--)
            r.append(temp[i]);

        return r.toString();
    }

}
