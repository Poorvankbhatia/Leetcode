/*

Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

 */

package dyanamicprogramming.hard;

/**
 * Created by poorvank on 21/02/17.
 */
public class WildCardMatching {

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        dp[0][0] = true;

        for (int i=1;i<=p.length();i++) {
            if(p.charAt(i-1)=='*') {
                dp[0][i] = dp[0][i-1];
            }
        }

        for (int i=1;i<=s.length();i++) {
            for (int j=1;j<=p.length();j++) {

                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1)=='*') {
                    /*
                      * includes ith character -  dp[i-1][j]
                      * 0 occurrences of star  -  dp[i][j-1]
                     */
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];

                } else {
                    dp[i][j] = false;
                }

            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.print(new WildCardMatching().isMatch("aa","*"));
    }

}


/*

Recurrence relation:

dp[i][j] = dp[i-1][j-1] // if s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?'

dp[i][j] = dp[i][j-1] || dp[i-1][j];


else
dp[i][j] = false

Do Look:

https://discuss.leetcode.com/topic/3040/linear-runtime-and-constant-space-solution/2

 */