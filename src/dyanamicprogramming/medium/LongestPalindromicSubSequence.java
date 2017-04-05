/*

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank on 14/02/17.
 */
public class LongestPalindromicSubSequence {

    public int longestPalindromeSubseq(String s) {

        if(null==s || s.length()==0) {
            return 0;
        }

        int[][] dp = new int[s.length()][s.length()];

        for (int i=0;i<s.length();i++) {
            dp[i][i] = 1;
        }


        for (int gap=2;gap<=s.length();gap++) {

            for (int i=0;i<=s.length()-gap;i++) {

                int j = i+gap-1;

                if(s.charAt(i)==s.charAt(j) && gap==2) {
                    dp[i][j] = 2;
                } else if(s.charAt(i)==s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1]+2;
                } else {
                    /*
                        If corner characters are not equal then longest palindromic sub sequence will be Max of
                        Removing of 1 character from end, or removing both the corner characters.
                        dp[i+1][j] - > removing first character
                        dp[i][j-1] - > removing last character
                     */
                    dp[i][j] = Math.max(Math.max(dp[i][j-1],dp[i+1][j]),1);
                }

            }

        }

        return dp[0][s.length()-1];

    }

    public static void main(String[] args) {
        String s = "cnbbjclpplc";
        System.out.print(new LongestPalindromicSubSequence().longestPalindromeSubseq(s));
    }

}
