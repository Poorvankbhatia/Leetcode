/*

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 20/02/17.
 */
public class RegularExpression {

    public boolean isMatch(String s, String p) {

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        dp[0][0] = true;

        for (int i=2;i<=p.length();i++) {
            if(p.charAt(i-1)=='*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i=1;i<=s.length();i++) {
            for (int j=1;j<=p.length();j++) {

                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1)=='*') {
                    //Consider zero occurrences of character before *
                    dp[i][j] = dp[i][j-2];
                    /*
                    Consider atleast one occurance of character before *
                    p.charAt(j-2)=='.' for cases like .*
                    s.charAt(i-1)==p.charAt(j-2) for cases like s=xaa && p=xa*
                     */
                    if(p.charAt(j-2)=='.' || s.charAt(i-1)==p.charAt(j-2)) {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                } else {
                    dp[i][j] = false;
                }

            }
        }

        return dp[s.length()][p.length()];

    }

    public static void main(String[] args) {
        System.out.print(new RegularExpression().isMatch("abcccde","abc*de"));
    }

}



/*

Recurrence relation:

dp[i][j] = dp[i-1][j-1] // if s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.'

dp[i][j] = dp[i][j-2]; // Counting zero occurrences

dp[i][j] = dp[i-1][j]; // p.charAt(j-2)=='.' || s.charAt(i-1)==p.charAt(j-2)

else
dp[i][j] = false

 */