/*

We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.

Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with
difficulty at most worker[i].

Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.

What is the most profit we can make?

Example 1:

Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
Notes:

1 <= difficulty.length = profit.length <= 10000
1 <= worker.length <= 10000
difficulty[i], profit[i], worker[i]  are in range [1, 10^5]

 */
package binarysearch.medium;

import java.util.*;

/**
 * Created by poorvank.b on 29/04/18.
 */
public class ProfitAssigningWork {

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        List<int[]> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
            list.add(new int[]{difficulty[i],profit[i]});
        }
        list.sort((a,b)->(a[0]-b[0]!=0?a[0]-b[0]:b[1]-a[1]));
        int[] max = new int[n];
        max[0]=list.get(0)[1];// Maximum Value till a given index.
        for (int i=1;i<n;i++) {
            max[i]=Math.max(list.get(i)[1],max[i-1]);
        }
        int ans=0;
        for(int w: worker) {
            int res = bs(w,list);
            ans+=res==-1?0:max[res];
        }
        return ans;
    }
    // Smallest index which is less than or equal to the given target.
    private int bs(int target,List<int[]> list) {
        int lo = 0;
        int hi = list.size()-1;
        if(target>=list.get(hi)[0]) {
            return hi;
        }
        int ans = -1;
        while (lo<hi) {
            int mid = (lo)+(hi-lo)/2;
            int val = list.get(mid)[0];
            if(target>=val) {
                ans=mid;
                lo=mid+1;
            } else {
                hi=mid;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] difficulty = new int[]{9,16,16,29,35,41,42,46,54,57,61,63,69,72,75,76,84,93,93,95};
        int[] profit = new int[]    {4,7,18,22,25,32,34,35,38,44,51,60,61,71,74,77,89,95,96,96};
        int[] worker = new int[]    {93};
        System.out.println(new ProfitAssigningWork().maxProfitAssignment(difficulty,profit,worker));
    }

}

/*

No Sort Sol:

public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[] dp = new int[100001];
        for (int i = 0; i < difficulty.length; i++) {
            dp[difficulty[i]] = Math.max(dp[difficulty[i]], profit[i]);
        }
        for (int i = 0; i < dp.length; i++) {
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
        }
        int sum = 0;
        for (int i : worker) {
            sum += dp[i];
        }
        return sum;
    }

 */