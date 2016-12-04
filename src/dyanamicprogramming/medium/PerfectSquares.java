/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 18/09/16.
 */
public class PerfectSquares {

        public int numSquares(int n) {

            if(n==0) {
                return 1;
            }
            if(n==1) {
                return 1;
            }
            int[] dp = new int[n+1];

            dp[1] = 1;
            dp[2] = 2;

            for (int i=3;i<=n;i++) {
                if(isPerfectSquare(i)) {
                    dp[i] = 1;
                    continue;
                }

                int max = Integer.MAX_VALUE;

                for (int j=1;j<i;j++) {
                    if(max>(dp[i-j] + dp[j])) {
                        max = dp[i-j] + dp[j];
                    }
                }

                dp[i] = max;

            }

            return dp[n];

        }

        private Boolean isPerfectSquare(int input)
        {
            long sqrt = (long) Math.sqrt(input);
            return ((sqrt * sqrt) == input);
        }


    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(9715));
    }

}
