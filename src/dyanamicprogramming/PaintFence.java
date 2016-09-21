/*

There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the posts such
that no more than two adjacent fence posts have the same color. Return the total number of ways you can paint the fence.


 */
package dyanamicprogramming;

/**
 * Created by poorvank on 11/09/16.
 */
public class PaintFence {

    public int numWays(int n, int k) {

        int[] dp = new int[n+1];
        dp[1] = k;
        dp[2] = (k*k);

        for (int i=3;i<=n;i++) {
            dp[i] = (k-1)*(dp[i-1]+dp[i-2]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        PaintFence pf =new PaintFence();
        System.out.println(pf.numWays(3,3));
    }
}

/*

 if both the previous fences have same color than the 3rd fence will have -> (k-1)*k combinations
 if both have different colors then the nexyt fence will have (k)*(k-1)*k combinations

 Hence total = (k-1)*(k+square(k)) == (k-1)*(dp[i-1]+dp[i-2]);

 */