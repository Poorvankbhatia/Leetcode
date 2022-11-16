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

    private static class Job {
        int start, finish, profit;
        public Job(int start, int finish, int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for(int i=0;i<n;i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        return scheduleApt(jobs);
    }

    private int scheduleApt(Job[] jobs) {
        // Sort jobs according to finish time
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.finish));
        // dp[i] stores the profit for jobs till jobs[i]
        // (including jobs[i])
        int n = jobs.length;
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        for (int i=1; i<n; i++) {
            // Profit including the current job
            int profit = jobs[i].profit;
            int l = search(jobs, i);
            if (l != jobs.length)
                profit += dp[l];
            // Store maximum of including and excluding
            dp[i] = Math.max(profit, dp[i-1]);
        }

        return dp[n-1];
    }

    private int search(Job[] jobs, int index) {
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
    }

}

/*

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
 */
