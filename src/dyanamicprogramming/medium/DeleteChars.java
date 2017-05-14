/*

Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 14/05/17.
 */
public class DeleteChars {

    public int minDistance(String word1, String word2) {

        if(word1.length()==0) {
            return word2.length();
        } else if(word2.length()==0) {
            return word1.length();
        }

        int m = word1.length();
        int n = word2.length();

        int dp[][] = new int[m+1][n+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i==0)
                    dp[i][j] = j;
                else if (j==0)
                    dp[i][j] = i;

                else if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];

                else
                    dp[i][j] = 1+ Math.min(dp[i-1][j],dp[i][j-1]);

            }
        }

        return dp[m][n];

    }

    public static void main(String args[])
    {
        String str1 = "dinitrophenylhydrazine";
        String str2 = "benzalphenylhydrazone";
        System.out.println(new DeleteChars().minDistance(str1,str2));
    }

}

// Same as edit distance