/*

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a substring of "cabac" because we can delete the first "c".
str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

 */
package dyanamicprogramming.hard;

public class ShortestSuperSequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            dp[i][j - 1]);
            }
        }

        int l = dp[m][n]; // Length of the ShortestSuperSequence
        char[] arr = new char[l];
        int i=m, j=n;
        while(i>0 && j>0)
        {
            /* If current character in str1 and str2 are same, then
             current character is part of shortest supersequence */
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                arr[--l] = str1.charAt(i-1);
                i--;j--;
            }else if(dp[i-1][j]<dp[i][j-1]) {
                arr[--l] = str1.charAt(i-1);
                i--;
            }
            else {
                arr[--l] = str2.charAt(j-1);
                j--;
            }
        }
        while (i > 0) {
            arr[--l] = str1.charAt(i-1);
            i--;
        }
        while (j > 0) {
            arr[--l] = str2.charAt(j-1);
            j--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String str1 = "abac";
        String str2 = "cab";
        System.out.println(new ShortestSuperSequence().shortestCommonSupersequence(str1,str2));
    }

}

/*

DP Recurrence:

Let str1[0..m - 1] and str2[0..n - 1] be two strings with lengths m and n .

  if (m == 0) return n;
  if (n == 0) return m;

  // If last characters are same, then add 1 to result and recur for str1[]
  if (str1.charAt(m - 1) == str2.charAt(n - 1))
     return 1 + shortestCommonSupersequence(str1, str2, m - 1, n - 1);

  // Else find shortest of following two
  //  a) Remove last character from str1 and recur
  //  b) Remove last character from str2 and recur
  else return 1 + min( shortestCommonSupersequence(str1, str2, m - 1, n), shortestCommonSupersequence(str1, str2, m, n - 1) );

  We start from the bottom-right most cell of the matrix and push characters in output string based on below rules-

 1. If the characters corresponding to current cell (i, j)
    in X and Y are same, then the character is part of shortest
    supersequence. We append it in output string and move
    diagonally to next cell (i.e. (i - 1, j - 1)).

 2. If the characters corresponding to current cell (i, j)
    in X and Y are different, we have two choices -

    If matrix[i - 1][j] > matrix[i][j - 1],
    we add character corresponding to current
    cell (i, j) in string Y in output string
    and move to the left cell i.e. (i, j - 1)
    else
    we add character corresponding to current
    cell (i, j) in string X in output string
    and move to the top cell i.e. (i - 1, j)

 3. If string Y reaches its end i.e. j = 0, we add remaining
    characters of string X in the output string
    else if string X reaches its end i.e. i = 0, we add
    remaining characters of string Y in the output string.

 */
