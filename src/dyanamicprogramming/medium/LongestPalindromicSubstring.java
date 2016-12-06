/*

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank on 06/12/16.
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        int n = s.length();
        if(n==0) {
            return "";
        }
        boolean[][] table = new boolean[n+1][n+1];

        int maxLength = 1;

        //All sub strings of length 1 are palindromes
        for (int i=0;i<=n;i++) {
            table[i][i] = true;
        }

        int start=0;
        for (int i=0;i<n-1;i++) {
            if(s.charAt(i)==s.charAt(i+1)) {
                table[i][i+1] = true;
                start = i;
                maxLength=2;
            }
        }


        for (int gap=3;gap<=n;gap++) {

            //If n==4 so i will go up to 1
            for (int i=0;i<=n-gap;i++) {

                int j = i+gap-1;
                /*
                    If characters at the start and end are same then we just need to verify if the middle ones are alo the same
                 */
                if(s.charAt(i)==s.charAt(j) && table[i+1][j-1]) {
                    table[i][j] = true;
                    if(gap>maxLength) {
                        start = i;
                        maxLength=gap;
                    }
                }

            }

        }

        return s.substring(start,start+maxLength);
    }

}


/*

Recursive relation:
s.charAt(i)==s.charAt(j) check if s(i+1..j-1)+1

So we calculate palindrome length from smallest substring.

Similar to longest palindromic subsequence:
We maintain a boolean table[n][n] that is filled in bottom up manner.
The value of table[i][j] is true, if the substring is palindrome, otherwise false.
To calculate table[i][j], we first check the value of table[i+1][j-1], if the value is true and str[i] is same as str[j],
then we make table[i][j] true. Otherwise, the value of table[i][j] is made false.

 */