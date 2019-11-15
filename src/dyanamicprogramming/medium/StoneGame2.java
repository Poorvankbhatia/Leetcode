/*

Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive
integer number of stones piles[i].  The objective of the game is to end with the most stones.

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.



Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10
piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.


Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4

 */
package dyanamicprogramming.medium;

public class StoneGame2 {

    private int[] sums;//the sum from piles[i] to the end
    private int[][] hash;
    public int stoneGameII(int[] piles) {
        if(piles == null || piles.length == 0) return 0;
        int n = piles.length;
        sums = new int[n];
        sums[n-1] = piles[n-1];
        for(int i = n -2; i>=0;i--) {
            sums[i] = sums[i+1] + piles[i]; //the sum from piles[i] to the end
        }

        hash = new int[n][n];
        return helper(piles, 0, 1);
    }

    private int helper(int[] piles, int i, int M) {
        if(i == piles.length) {
            return 0;
        }
        if(2*M >= piles.length - i) {
            return sums[i];
        }
        if(hash[i][M] != 0) {
            return hash[i][M];
        }
        int min = Integer.MAX_VALUE;//the min value the next player can get
        for(int x=1;x<=2*M;x++){
            min = Math.min(min, helper(piles, i+x, Math.max(M,x)));
        }
        hash[i][M] = sums[i] - min;  //max stones = all the left stones - the min stones next player can get
        return hash[i][M];
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,7,9};
        System.out.println(new StoneGame2().stoneGameII(a));
    }
}

/*

hash[i, m] = maximum stones the current player can get from piles[i:] with M=m
A[i]= total stones of piles[i:]
when current player pick stones from i to i+x-1
-> the other player's stones: dp[i+x, max(m, x)]
-> total stones of current player: A[i] - dp[i+x, max(m, x)]
we want the current player gets maximum means the other player gets minimum

 */
