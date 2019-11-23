/*

We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)



Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation:
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.


Note:

1 <= stones.length <= 30
1 <= stones[i] <= 100

 */
package dyanamicprogramming.medium;

public class LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum=0;
        for(int stone : stones) {
            sum+=stone;
        }
        boolean[][] dp =new boolean[n+1][sum+1];
        int max= 0;
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<=sum/2;j++) {
                if(i==0 && j==0) {
                    dp[i][j]=true;
                } else if(i==0) {
                    dp[i][j]=false;
                } else if(j==0) {
                    dp[i][j]=true;
                } else {
                    dp[i][j] = dp[i-1][j];
                    if(stones[i-1]<=j) {
                        dp[i][j] |= (dp[i-1][j-stones[i-1]]);
                    }
                }
                if(dp[i][j]) {
                    max = Math.max(max,j);
                }
            }
        }

        return sum-(2*max);

    }

}

/*

This question equals to partition an array into 2 subsets whose difference is minimal
(1) S1 + S2  = S
(2) S1 - S2 = diff

==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2

Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this

dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
    i ranges from (sum of all elements) {1..n}
    j ranges from  {1..n}

Suppose you have rock a, b, c and d.
If you subtract them in the following order: b-c, then d-b-c. Then it is the same as doing d-(b+c).
Then doing [d-(b+c)]-a is the same as -a+d-(b+c), which is d-a-(b+c), which is d-[a+(b+c)], which is d-(a+b+c). (So doing things in that order will lead to this shortcut).

Lets try another order.
Suppose you have rock a, b, c and d.
If you do a-d, then b-c, then (a-d)-(b-c).
Then (a-d)-(b-c) is the same as a-d-b+c, which is the same as -d-b+a+c, which is -(d+b)+(a+c), which is (a+c)-(d+b). Another shortcut.

Then you can see that depending on the order of the subtractions, we get a different setting of difference between two groups.

 */