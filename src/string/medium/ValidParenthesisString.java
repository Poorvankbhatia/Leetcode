/*
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
 */
package string.medium;

/**
 * Created by poorvank.b on 17/09/17.
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {

        int n = s.length();
        return isValid(s,0,0,n,0);

    }

    private boolean isValid(String s,int start,int end,int n,int index) {

        if(start==end && index==n) {
            return true;
        }

        if(index>=n || end>start) {
            return false;
        }

        char c = s.charAt(index);

        if(c=='(') {
            return isValid(s,start+1,end,n,index+1);
        } else if(c==')') {
            return isValid(s,start,end+1,n,index+1);
        } else {
            return isValid(s,start,end,n,index+1) || isValid(s,start+1,end,n,index+1) || isValid(s,start,end+1,n,index+1);
        }

    }

    public static void main(String[] args) {
        String s = ")(";
        System.out.println(new ValidParenthesisString().checkValidString(s));
    }

}

/*

the idea is to similar to the solution of without '*'. keep a lower bound of '(' counts and an upper bound of '(' count.

public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }

 */