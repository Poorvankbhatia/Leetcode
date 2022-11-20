/*

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that
there are no 2 jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.


Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.


Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4



 */
package dyanamicprogramming.hard;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for(int i=0;i<n;i++) {
            jobs[i] = new int[]{startTime[i],endTime[i],profit[i]};
        }
        // Sort by finish time.
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        int[] dp = new int[n];
        dp[0] = jobs[0][2];
        for(int i=1;i<n;i++) {
            // figure out the current profit.
            int currentProfit = jobs[i][2];
            // find the last non overlapping job if any.
            int lastNonOverlappingJob = lastJob(jobs,i);
            if(lastNonOverlappingJob!=-1) {
                currentProfit += dp[lastNonOverlappingJob];
            }
            // get max of current or last.
            dp[i] = Math.max(currentProfit,dp[i-1]);
        }
        return dp[n-1];
    }

    private int lastJob(int[][] jobs,int i) {
        int start = 0;
        int end = jobs.length-1;
        while(end-start>1) {
            int mid = start + (end-start)/2;
            // if finish time of mid>start of current, it overlaps move left.
            if(jobs[mid][1]>jobs[i][0]) {
                end = mid-1;
            } else {
                start = mid;
            }
        }
        return jobs[end][1]<=jobs[i][0]?end:jobs[start][1]<=jobs[i][0]?start:-1;
    }

    /*private int lastJob(Job[] jobs, int index) {
        int start = 0, end = jobs.length - 1, nextIndex = jobs.length;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (jobs[mid].finish > jobs[index].start) {
                end = mid - 1;
            } else {
                nextIndex = mid;
                start = mid + 1;
            }
        }
        return nextIndex;
    }*/

}

/*
O(nlogn)

starting from job index cur = jobs.length - 1, we might schedule the jobs[cur] or not.

If we schedule jobs[cur], the problem becomes profit of jobs[cur] + max profit of scheduling jobs ending with nearest previous job index.
If we don't schedule jobs[cur], the problem becomes max profit of scheduling jobs ending with cur - 1.

-> Create a Job array for ease of calculation.
-> Sort jobs according to finish time.
-> For the job array jobs
   maxprofit(int[] jobs, n){
     a) if (n == 1) return jobs[0];
     b) Return the maximum of following two profits.
         -> Maximum profit by excluding current job :  maxprofit(jobs, n-1)
         -> Maximum profit by including the current job
Find Profit including current Job:
Find the latest job before the current job (in sorted array) that doesn't conflict with current job 'jobs[n-1]'.
Once found, we recur for all jobs till that job and add profit of current job to result.

Complexity : O(n Log n)

Note:

If we sort jobs by start time, starting from job index cur = 0, we might either schedule the jobs[cur] or not.

If we schedule jobs[cur], the problem becomes profit of jobs[cur] + max profit of scheduling jobs starting from next available job index.
If we don't schedule jobs[cur], the problem becomes max profit of scheduling jobs starting from cur + 1.

class Solution {
    private Map<Integer, Integer> dp;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        dp = new HashMap<>();
        return dfs(0, jobs);
    }

    private int dfs(int cur, int[][] jobs) {
        if (cur == jobs.length) {
            return 0;
        }

        if (dp.containsKey(cur)) {
            dp.get(cur);
        }

        int next = findNext(cur, jobs);
        int inclProf = jobs[cur][2] + (next == -1 ? 0 : dfs(next, jobs));
        int exclProf = dfs(cur + 1, jobs);

        dp.put(cur, Math.max(inclProf, exclProf));
        return Math.max(inclProf, exclProf);
    }

    int findNext(int cur, int[][] jobs) {
        for (int next = cur + 1; next < jobs.length; next++) {
            if (jobs[next][0] >= jobs[cur][1]) {
                return next;
            }
        }
        return -1;
    }
}

 */
