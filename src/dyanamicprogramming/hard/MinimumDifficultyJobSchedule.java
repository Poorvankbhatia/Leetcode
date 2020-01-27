/*
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each
day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.

Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7
Example 2:

Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:

Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
Example 4:

Input: jobDifficulty = [7,1,7,1,7,1], d = 3
Output: 15
Example 5:

Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
Output: 843


Constraints:

1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10

 */
package dyanamicprogramming.hard;

import java.util.Arrays;

public class MinimumDifficultyJobSchedule {

    int[][][] dp; // cache for minimum value between a range(start,end)
    int[][] max; // Cache for max value between a range(start,end)
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(d>n) {
            return -1;
        }
        dp = new int[301][301][11];
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b,-1);
            }
        }
        max = new int[n][n];
        for (int[] a : max) {
            Arrays.fill(a,-1);
        }
        return util(0,jobDifficulty.length-1,d,jobDifficulty);
    }

    private int util(int start,int end,int d,int[] jobDifficulty) {
        // If start==end & more number of days are left, u can't arrange.
        if(start==end) {
            return d==1?jobDifficulty[start]:3000001;
        }
        if(d==1) {
            return getMax(start,end,jobDifficulty);
        }
        if(dp[start][end][d]!=-1) {
            return dp[start][end][d];
        }
        int sum=Integer.MAX_VALUE;
        for (int i=start;i<end;i++) {
            int max = getMax(start,i,jobDifficulty);
            sum = Math.min(max+util(i+1,end,d-1,jobDifficulty),sum);
        }
        dp[start][end][d]=sum;
        return sum;
    }

    private int getMax(int start,int end,int[] a) {
        if(max[start][end]!=-1) {
            return max[start][end];
        }
        int maxVal = Integer.MIN_VALUE;
        for (int j=start;j<=end;j++) {
            maxVal = Math.max(maxVal,a[j]);
        }
        max[start][end]=maxVal;
        return maxVal;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumDifficultyJobSchedule().minDifficulty(new int[]{11,111,22,222,33,333,44,444},6));
    }

}

/*

Efficient solution:

public int minDifficulty(int[] jobDifficulty, int d) {

        static int inf = Integer.MAX_VALUE;
    public static int function(int[] jobDifficulty, int n, int i, int d, int[][] dp) {
        if(d == 0 && i == n) return 0;
        if(d == 0 || i == n) return inf;
        if(dp[i][d] != -1) return dp[i][d];
        int currMax = jobDifficulty[i];
        int min = inf;
        for(int j = i; j < n; j++) {
            currMax = Math.max(jobDifficulty[j], currMax);
            int temp = function(jobDifficulty, n, j + 1, d - 1, dp);
            if(temp != inf) min = Math.min(min, temp + currMax);
        }
        return dp[i][d] = min;
    }
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(d > n) return -1;
        int[][] dp = new int[n][d + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < d + 1; j++) dp[i][j] = -1;
        }
        return function(jobDifficulty, n, 0, d, dp);
    }

 */
