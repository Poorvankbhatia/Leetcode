/*

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

 */
package arrays.medium;

/**
 * Created by poorvank.b on 15/10/17.
 */
public class KSubSets {

    public boolean canPartitionKSubsets(int[] arr, int K) {

        int N = arr.length;
        //  If K is 1, then complete array will be our answer
        if (K == 1)
            return true;

        //  If total number of partitions are more than N, then
        // division is not possible
        if (N < K)
            return false;

        // if array sum is not divisible by K then we can't divide
        // array into K partitions
        int sum = 0;
        for (int anArr : arr)
            sum += anArr;
        if (sum % K != 0)
            return false;

        //  the sum of each subset should be subset (= sum / K)
        int subset = sum / K;
        int subsetSum[] = new int[K];
        boolean taken[] = new boolean[N];

        //  Initialize sum of each subset from 0
        for (int i = 0; i < K; i++)
            subsetSum[i] = 0;

        //  mark all elements as not taken
        for (int i = 0; i < N; i++)
            taken[i] = false;

        // initialize first subsubset sum as last element of
        // array and mark that as taken
        subsetSum[0] = arr[N - 1];
        taken[N - 1] = true;

        //  call recursive method to check K-substitution condition
        return isKPartitionPossibleRec(arr, subsetSum, taken,
                subset, K, N, 0, N - 1);



    }

    private boolean isKPartitionPossibleRec(int arr[], int subsetSum[], boolean taken[],
                                    int subset, int K, int N, int curIdx, int limitIdx)
    {
        if (subsetSum[curIdx] == subset)
        {
        /*  current index (K - 2) represents (K - 1) subsets of equal
            sum last partition will already remain with sum 'subset'*/
            if (curIdx == K - 2)
                return true;

            //  recursive call for next subsetition
            return isKPartitionPossibleRec(arr, subsetSum, taken, subset,
                    K, N, curIdx + 1, N - 1);
        }

        //  start from limitIdx and include elements into current partition
        for (int i = limitIdx; i >= 0; i--)
        {
            //  if already taken, continue
            if (taken[i])
                continue;
            int tmp = subsetSum[curIdx] + arr[i];

            // if temp is less than subset then only include the element
            // and call recursively
            if (tmp <= subset)
            {
                //  mark the element and include into current partition sum
                taken[i] = true;
                subsetSum[curIdx] += arr[i];
                boolean next = isKPartitionPossibleRec(arr, subsetSum, taken,
                        subset, K, N, curIdx, i - 1);

                // after recursive call unmark the element and remove from
                // subsetition sum
                taken[i] = false;
                subsetSum[curIdx] -= arr[i];
                if (next)
                    return true;
            }
        }
        return false;
    }

    /*

    We can solve this problem recursively, we keep an array for sum of each partition and a boolean array to check whether an element is
     already taken into some partition or not.
First we need to check some base cases,
If K is 1, then we already have our answer, complete array is only subset with same sum.
If N < K, then it is not possible to divide array into subsets with equal sum, because we can’t divide the array into more than N parts.
If sum of array is not divisible by K, then it is not possible to divide the array. We will proceed only if k divides sum. Our goal reduces
 to divide array into K parts where sum of each part should be array_sum/K
In below code a recursive method is written which tries to add array element into some subset. If sum of this subset reaches required sum,
 we iterate for next part recursively, otherwise we backtrack for different set of elements. If number of subsets whose sum reaches the
  required sum is (K-1), we flag that it is possible to partition array into K parts with equal sum, because remaining elements already
   have a sum equal to required sum.

     */

}
