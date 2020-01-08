/*

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 11/01/17.
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        if(word1==null || word1.length()==0) {
            return word2.length();
        }
        if(word2==null || word2.length()==0) {
            return word1.length();
        }
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
            for(int j=0;j<=n;j++) {
                if(i==0) {
                    dp[i][j]=j;
                } else if(j==0) {
                    dp[i][j]=i;
                } else if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1];
                } else {
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }
        return dp[m][n];
    }

    private int min(int x,int y,int z) {
        return Math.min(z,Math.min(x,y));
    }

    public static void main(String args[])
    {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println(new EditDistance().minDistance(str1,str2));
    }

}


/*

The idea is process all characters one by one staring from either from left or right sides of both strings.
Let we traverse from right corner, there are two possibilities for every pair of character being traversed.

m: Length of str1 (first string)
n: Length of str2 (second string)
If last characters of two strings are same, nothing much to do. Ignore last characters and get count for remaining strings.
 So we recur for lengths m-1 and n-1.
Else (If last characters are not same), we consider all operations on ‘str1’, consider all three operations on last character
 of first string, recursively compute minimum cost for all three operations and take minimum of three values.
Insert: Recur for m and n-1
Remove: Recur for m-1 and n
Replace: Recur for m-1 and n-1

Time Complexity: O(m x n)
Auxiliary Space: O(m x n)


The time complexity of above solution is exponential. In worst case, we may end up doing O(3^m) operations.
The worst case happens when none of characters of two strings match.


 */