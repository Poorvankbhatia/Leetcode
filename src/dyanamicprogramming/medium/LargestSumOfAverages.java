/*

We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group.
What is the largest score we can achieve?

Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input:
A = [9,1,2,3,9]
K = 3
Output: 20
Explanation:
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.


Note:

1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
Answers within 10^-6 of the correct answer will be accepted as correct.

 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 08/04/18.
 */
public class LargestSumOfAverages {

    Map<String,Double> map;
    public double largestSumOfAverages(int[] A, int K) {
        map = new HashMap<>();
        return util(A,K,0,A.length-1);
    }

    private double util(int[] A,int K,int start,int end) {
        String key = start+"_"+end+"_"+K;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        if(K==1) {
            double sum = 0;
            for(int i=start;i<=end;i++) {
                sum+=A[i];
            }
            double avg = sum/(end-start+1);
            map.put(key,avg);
            return avg;
        }
        double sum=0.0;
        double max = 0.0;
        for(int i=start;i<end;i++) {
            sum+=A[i];
            double avg = (sum/(i-start+1));
            avg += util(A,K-1,i+1,end);
            max = Math.max(avg,max);
        }
        map.put(key,max);
        return max;
    }



    public static void main(String[] args) {
        int[] A = new int[]{4663,3020,7789,1627,9668,1356,4207,1133,8765,4649,205,6455,8864,3554,3916,5925,3995,4540,3487,5444,8259,8802,
                6777,7306,989,4958,2921,8155,4922,2469,6923,776,9777,1796,708,786,3158,7369,8715,2136,2510,3739,6411,7996,6211,8282,4805,236,1489,7698};
        System.out.println(new LargestSumOfAverages().largestSumOfAverages(A,27));
    }

}

/*

Using 3D array:

Double[][][] dp;
    public double largestSumOfAverages(int[] A, int K) {
        dp = new Double[100][100][100];
        return util(A,K,0,A.length-1);
    }

    private double util(int[] A,int K,int start,int end) {
        if(dp[start][end][K]!=null) {
            return dp[start][end][K];
        }
        if(K==1) {
            double sum = 0;
            for(int i=start;i<=end;i++) {
                sum+=A[i];
            }
            double avg = sum/(end-start+1);
            dp[start][end][K]=avg;
            return avg;
        }
        double sum=0.0;
        double max = 0.0;
        for(int i=start;i<end;i++) {
            sum+=A[i];
            double avg = (sum/(i-start+1));
            avg += util(A,K-1,i+1,end);
            max = Math.max(avg,max);
        }
        dp[start][end][K]=max;
        return max;
    }

----------

Using n(element count) & K as key:
public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[][] memo = new double[N+1][N+1];
        double cur = 0;
        for (int i = 0; i < N; ++i) {
            cur += A[i];
            memo[i + 1][1] = cur / (i + 1);
        }
        return search(N, K, A, memo);
    }

    public double search(int n, int k, int[] A, double[][] memo) {
        if (memo[n][k] > 0) return memo[n][k];
        double cur = 0;
        for (int i = n - 1; i > 0; --i) {
            cur += A[i];
            memo[n][k] = Math.max(memo[n][k], search(i, k - 1, A, memo) + cur / (n - i));
        }
        return memo[n][k];
    }




 */