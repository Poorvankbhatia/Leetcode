/*

Given a non-empty string check if it can be constructed by taking a substring of it and
appending multiple copies of the substring together. You may assume the given string consists of lowercase
English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

 */
package string.easy;

/**
 * Created by poorvank on 13/11/16.
 */
public class RepeatedSubstring {

    public boolean repeatedSubstringPattern(String str) {

        if(null==str) {
            return false;
        }


        for (int i=1;i<=str.length()/2;i++) {
            if(isPattern(str,i)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPattern(String str,int l) {

        if(str.length()%l!=0) {
            return false;
        }

        for (int j=l;j<str.length();j++) {
            if(str.charAt(j%l)!=str.charAt(j)) {
                return false;
            }
        }

        return true;


    }

    public static void main(String[] args) {

        System.out.print(new RepeatedSubstring().repeatedSubstringPattern("ab"));

    }

}
