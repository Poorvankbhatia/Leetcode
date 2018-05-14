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

    private class Element {
        private int profit;
        private int difficulty;

        Element(int profit, int difficulty) {
            this.profit = profit;
            this.difficulty = difficulty;
        }

    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        int n = profit.length;

        Element[] arr = new Element[n];

        for (int i=0;i<n;i++) {
            arr[i]=new Element(profit[i],difficulty[i]);
        }

        Arrays.sort(arr,(a,b)->a.difficulty!=b.difficulty?a.difficulty-b.difficulty:a.profit-b.profit);

        for (int i=1;i<n;i++) {
            arr[i].profit = Math.max(arr[i-1].profit,arr[i].profit);
        }

        int maxProfit = 0;

        for (int w : worker) {
            maxProfit+=binarySearch(arr,w,0,arr.length-1);
        }

        return maxProfit;

    }

    private int binarySearch(Element[] arr,int w,int start,int end) {

        if(w<arr[start].difficulty) {
            return 0;
        }

        if(w>=arr[end].difficulty) {
            return arr[end].profit;
        }


        int mid = start + (end - start) / 2;
        if (arr[mid].difficulty == w) {
            // In case there are multiple Element with same difficult, choose the one with highest
            if (mid+1<=end) {
                if(arr[mid+1].difficulty>arr[mid].difficulty) {
                    return arr[mid].profit;
                } else {
                    return binarySearch(arr,w,mid+1,end);
                }
            }
            return arr[mid].profit;
        } else if (arr[mid].difficulty < w) {
            if (mid + 1 <= end && w < arr[mid + 1].difficulty) {
                return arr[mid].profit;
            } else {
                return binarySearch(arr, w, mid + 1, end);
            }
        } else {
            if (mid - 1 >= start && w > arr[mid - 1].difficulty) {
                return arr[mid-1].profit;
            } else {
                return binarySearch(arr,w,start,mid-1);
            }
        }

    }

    public static void main(String[] args) {
        int[] difficulty = new int[]{9,16,16,29,35,41,42,46,54,57,61,63,69,72,75,76,84,93,93,95};
        int[] profit = new int[]    {4,7,18,22,25,32,34,35,38,44,51,60,61,71,74,77,89,95,96,96};
        int[] worker = new int[]    {93};
        System.out.println(new ProfitAssigningWork().maxProfitAssignment(difficulty,profit,worker));
    }

}
