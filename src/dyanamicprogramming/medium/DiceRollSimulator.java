/*

A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i
more than rollMax[i] (1-indexed) consecutive times.

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at
rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
Example 2:

Input: n = 2, rollMax = [1,1,1,1,1,1]
Output: 30
Example 3:

Input: n = 3, rollMax = [1,1,1,2,2,3]
Output: 181


Constraints:

1 <= n <= 5000
rollMax.length == 6
1 <= rollMax[i] <= 15

 */
package dyanamicprogramming.medium;

public class DiceRollSimulator {
    int[][][] dp = new int[5001][6][16];
    private int mod=(int)(Math.pow(10,9))+7;
    public int dieSimulator(int n, int[] rollMax) {
        return util(n,rollMax,-1,0);
    }
    // Last is kept since consecutive occurrence is not allowed
    private int util(int n,int[] rollMax,int last,int len) {
        if(n==0) {
            return 1;
        }
        if(last>=0 && dp[n][last][len]!=0) {
            return dp[n][last][len];
        }
        int ans = 0;
        for(int i=0;i<6;i++) {
            if (rollMax[i] != len || i != last) {
                ans=(ans+util(n-1,rollMax,i,i==last?len+1:1))%mod;
            }
        }
        if(last>=0) {
            dp[n][last][len] = ans;
        }
        return ans%mod;
    }
}

/*

Define dp[i][j][k]: ith turn end with j continues number of k
in each turn i, for each i length sequence ending with k, we search previous i-1 length sequences end with prev;
and in those previous sequence, we iterate through j continues number of prev.
e.g.
we get into i==5 turn with end '2', 'xxxx2'
then
dp[i][4][2] = 'x2222' = 'x222' + '2'
dp[i][3][2] = 'xx222' = 'xx22' + '2'
dp[i][2][2] = 'xxx22' = 'xxx2' + '2'
dp[i][1][2] = 'xxxx2' = ('xxx1' + '2') + ('xx11'+'2') + ('x111'+'2') + ('1111'+'2') + ('xxx3'+'2')+('xx33'+'2')...
as long as prev is under rollmax[prev]

 */