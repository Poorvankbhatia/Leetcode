/*

Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.



Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
Example 4:

Input: s = "g"
Output: 0
Example 5:

Input: s = "no"
Output: 1


Constraints:

1 <= s.length <= 500
All characters of s are lower case English letters.

 */
package dyanamicprogramming.hard;

public class MinimumInsertionSteps {

    public int minInsertions(String s) {

        if(s==null || s.length()==0) {
            return 0;
        }
        String rev = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[s.length()+1][rev.length()+1];
        for (int i=0;i<=s.length();i++) {
            for (int j=0;j<=s.length();j++) {
                if(i==0) {
                    dp[i][j]=0;
                } else if(j==0) {
                    dp[i][j]=i;
                } else if(s.charAt(i)==rev.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+1;
                }
            }
        }

        return s.length()-dp[s.length()][rev.length()];

    }

}

/*

The problem of finding minimum insertions can also be solved using Longest Common Subsequence (LCS) Problem.
If we find out LCS of string and its reverse, we know how many maximum characters can form a palindrome. We need insert remaining characters. Following are the steps.

-> Find the length of LCS of input string and its reverse. Let the length be ‘l’.
-> The minimum number insertions needed is length of input string minus ‘l’.

 */