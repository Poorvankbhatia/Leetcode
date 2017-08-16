/*

Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.

 */
package arrays.Hard;

/**
 * Created by poorvank on 02/08/17.
 */

//COPIED
public class MaximumAverage2 {

    public double findMaxAverage(int[] nums, int k) {
        double max_val = Integer.MIN_VALUE;
        double min_val = Integer.MAX_VALUE;
        for (int n: nums) {
            max_val = Math.max(max_val, n);
            min_val = Math.min(min_val, n);
        }
        while (max_val-min_val > 0.00001) {
            double mid = (max_val + min_val) * 0.5;
            if (check(nums, mid, k))
                min_val = mid;
            else
                max_val = mid;
        }
        return min_val;
    }
    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - mid;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prev += nums[i - k] - mid;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {776,374,92,42,945,866,389,379,730,541,195,563,870,694,66,481,101,381,808,851,917,450,811,118,269,774,415,732,336,370,816,112,745,908,506,42,126,247,773,208,789,968,771,11,662,189,492,763,922,301,966,840,103,777,310,372,551,77,104,887,448,272,351,545,532,209,939,658,457,712,866,246,32,990,257,694,531,749,809,454,402,127,646,505,905,956,229,808,386,685,696,834,957,399,731,841,609,670,499,66,734,718,664,766,60,273,812,591,22,621,45,425,100,691,282,5,0,512,814,386,197,862,572,507,613,303,348,574,325,200,992,59,918,656,825,978,929,637,921,304,610,967,81,710,10,363,68,362,227,234,748,777,448,672,284,61,975,984,636,300,184,628,711,102,637,536,432,918,525,354,222,135,673,303,198,35,19,618,398,598,852,498,375,300,171,11,713,498,996,701,151,180,682,862,635,671,751,419,589,276,125,812,764,150,467,962,186,838,580,936,437,784,434,164,84,957,176,149,456,172,203,959,704,885,821,691,556,572,111,145,201,588,309,965,739,129,279,925,967,859,213,756,995,999,921,79,957,97,580,765,621,783,724,325,668,897,17,576,822,480,74,23,68,383,988,807,512,267,84,832,478,297,588,473,297,509,904,606,958,484,723,579,620,799,905,640,48,274,217,870,754,643,893,174,26,233,982,891,852,418,75,330,716,663,155,365,525,59,323,483,896,46,63,868,845,320,508,245,594,77,116,700,720,361,874,99,947,208,990,799,627,65,482,695,80,637,60,605,49,735,89,297,781,152,165,978,824,25,223,770,103,691,470,823,53,696,922,352,905,264,151,884,681,985,579,762,975,991,367,376,726,808,673,859,960,190,
                837,136,215,412,906,318,456,728,494,861,425,416,213,682,681,364,566,362,702,145,476,677,136,844,53,214,652};
        System.out.println(new MaximumAverage2().findMaxAverage(arr,398));
    }

}

/*


https://leetcode.com/articles/maximum-average-subarray-ii/

Using Binary Search [Accepted]

Algorithm

To understand the idea behind this method, let's look at the following points.

Firstly, we know that the value of the average could lie between the range (min, max)(min,max). Here, minmin and maxmax refer to the minimum and the maximum values out of the given numsnums array. This is because, the average can't be lesser than the minimum value and can't be larger than the maximum value.

But, in this case, we need to find the maximum average of a subarray with atleast kk elements. The idea in this method is to try to approximate(guess) the solution and to try to find if this solution really exists.

If it exists, we can continue trying to approximate the solution even to a further precise value, but choosing a larger number as the next approximation. But, if the initial guess is wrong, and the initial maximum average value(guessed) isn't possible, we need to try with a smaller number as the next approximate.

Now, instead of doing the guesses randomly, we can make use of Binary Search. With minmin and maxmax as the initial numbers to begin with, we can find out the midmid of these two numbers given by (min+max)/2(min+max)/2. Now, we need to find if a subarray with length greater than or equal to kk is possible with an average sum greater than this midmid value.

To determine if this is possible in a single scan, let's look at an observation. Suppose, there exist jj elements, a_1, a_2, a_3..., a_ja
​1
​​ ,a
​2
​​ ,a
​3
​​ ...,a
​j
​​  in a subarray within numsnums such that their average is greater than midmid. In this case, we can say that

(a1+a2+a3...+aj)/j≥mid(a1+a2+a3...+aj)/j≥mid or

(a1+a2+a3...+aj)≥j∗mid(a1+a2+a3...+aj)≥j∗mid or

(a1−mid)+(a2−mid)+(a3−mid)...+(aj−mid)≥0(a1−mid)+(a2−mid)+(a3−mid)...+(aj−mid)≥0

Thus, we can see that if after subtracting the midmid number from the elements of a subarray with more than k-1k−1 elements, within nums,
 if the sum of elements of this reduced subarray is greater than 0, we can achieve an average value greater than midmid. Thus, in this case, we
 need to set the midmid as the new minimum element and continue the process.

Otherwise, if this reduced sum is lesser than 0 for all subarrays with greater than or equal to kk elements, we can't achieve midmid as the average. Thus, we need to set midmid as the new maximum element and continue the process.

In order to determine if such a subarray exists in a linear manner, we keep on adding nums[i]-midnums[i]−mid to the sumsum obtained till the i^{th}i
​th
​​  element while traversing over the numsnums array. If on traversing the first kk elements, the sumsum becomes greater than or equal to 0, we can directly determine that we can increase the average beyond midmid. Otherwise, we continue making additions to sumsum for elements beyond the k^{th}k
​th
​​  element, making use of the following idea.

If we know the cumulative sum upto indices ii and jj, say sum_isum
​i
​​  and sum_jsum
​j
​​  respectively, we can determine the sum of the subarray between these indices(including jj) as sum_j - sum_isum
​j
​​ −sum
​i
​​ . In our case, we want this difference between the cumulative sums to be greater than or equal to 0 as discusssed above.

Further, for sum_isum
​i
​​  as the cumulative sum upto the current(i^{th}i
​th
​​ ) index, all we need is sumj−sumi≥0sumj−sumi≥0 such that j−i≥kj−i≥k.

To achive this, instead of checking with all possible values of sum_jsum
​j
​​ , we can just consider the minimum cumulative sum upto the index j - ij−i. This is because if the required condition can't be sastisfied with the minimum sum_jsum
​j
​​ , it can never be satisfied with a larger value.

To fulfil this, we make use of a prevprev variable which again stores the cumulative sums but, its current index(for cumulative sum) lies behind the current index for sumsum at an offset of kk units. Thus, by finding the minimum out of prevprev and the last minimum value, we can easily find out the required minimum sum value.

 */