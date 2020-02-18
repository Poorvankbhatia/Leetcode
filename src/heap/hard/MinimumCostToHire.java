/*

There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.



Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.


Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.

 */
package heap.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by poorvank.b on 15/08/18.
 */
public class MinimumCostToHire {

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int numWorkers = quality.length;

        /* qualityRatio[i] = {quality, wage[i] / quality[i]}. */
        double[][] qualityRatio = new double[numWorkers][2];

        for (int i = 0; i < numWorkers; i++) {
            qualityRatio[i][0] = quality[i];
            qualityRatio[i][1] = (double) wage[i] / quality[i];
        }

        Arrays.sort(qualityRatio, Comparator.comparingDouble(a -> a[1]));
        double minSumSalary = Integer.MAX_VALUE;
        int sumQuality = 0;;

        /* Always remove maximum quality. */
        PriorityQueue<Double> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b, a));

        for (int i = 0; i < numWorkers; i++) {
            maxHeap.add(qualityRatio[i][0]);
            sumQuality += qualityRatio[i][0];
            if (maxHeap.size() > K) {
                sumQuality -= maxHeap.poll();
            }
            if (maxHeap.size() == K) {

                /* Calculate sumSalary. */
                double curRatio = qualityRatio[i][1];
                minSumSalary = Math.min(minSumSalary, sumQuality * curRatio);
            }
        }

        return minSumSalary;
    }

}
/*
Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
->

 quality[i]   money[i]
 _________  = ________
 quality[j]   money[j]

 money[i] / quality[i] = money[j] / quality[j] -- ratio
That is, the group of people should have the same ratio.

Every worker in the paid group must be paid at least their minimum wage expectation.
->

money[i] >= wage[i]
ratio >= wage[i] / quality[i] -- ratio2
->
ratio is at least the maximum ratio2 within the group of K people.

If we keep current candidates in a priority queue as below,

|-- < k candidates--|
			new worker to join is with higher ratio2
If there are k + 1candidates, we pop the highest quality to reduce total cost.
If there are k candidates, we can calculate the total cost.




"1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group."
So for any two workers in the paid group,
we have wage[i] : wage[j] = quality[i] : quality[j]
So we have wage[i] : quality[i] = wage[j] : quality[j]
We pay wage to every worker in the group with the same ratio compared to his own quality.

"2. Every worker in the paid group must be paid at least their minimum wage expectation."
For every worker, he has an expected ratio of wage compared to his quality.

So to minimize the total wage, we want a small ratio.
So we sort all workers with their expected ratio, and pick up K first worker.
Now we have a minimum possible ratio for K worker and we their total quality.

As we pick up next worker with bigger ratio, we increase the ratio for whole group.
Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
We calculate the current ratio * total quality = total wage for this group.

We redo the process and we can find the minimum total wage.
Because workers are sorted by ratio of wage/quality.
For every ratio, we find the minimum possible total quality of K workers.

Time Complexity
O(NlogN) for sort.
O(NlogK) for priority queue.

 */
