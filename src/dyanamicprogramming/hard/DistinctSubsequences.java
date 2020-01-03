/*
    Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while
 "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 17/01/17.
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i=0;i<=s.length();i++) {
            for(int j=0;j<=t.length();j++) {
                if(j==0) {
                    dp[i][j]=1;
                } else if(i!=0) {
                    if(s.charAt(i-1)==t.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp[s.length()][t.length()];
    }


    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinct("rabbbit","rabbit"));
    }

}

/*

When you see string problem that is about subsequence or matching, dynamic programming method should come to mind naturally.
The key is to find the initial and changing condition.
Let W(i, j) stand for the number of subsequences of S(0, i) equals to T(0, j).
If S.charAt(i) == T.charAt(j), W(i, j) = W(i-1, j-1) + W(i-1,j); Otherwise, W(i, j) = W(i-1,j).

 */