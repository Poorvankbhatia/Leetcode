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
package binarysearch.hard;

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
    public boolean check(int[] nums, double target, int k) {
        double sum = 0, extraSum =0;
        for(int i=0; i<k; i++){
            sum += nums[i]-target;
        }
        // must have at least k elements; the nums before the last k element should be kept if their sum > 0;
        // else we should remove them from the current sum (equivalent to update the start position)
        int curr = k;
        while (curr < nums.length) {
            if (sum >= 0) {
                return true;
            }
            sum += nums[curr] - target;
            extraSum += nums[curr-k] - target;
            if (extraSum < 0) { //update the start position of the current sum
                sum -= extraSum;
                extraSum = 0;
            }
            curr ++;
        }
        return sum >= 0;
    }

    public static void main(String[] args) {
        int[] arr = {776,374,92,42,945,866,389,379,730,541,195,563,870,694,66,481,101,381,808,851,917,450,811,118,269,774,415,732,336,370,816,112,745,908,506,42,126,247,773,208,789,968,771,11,662,189,492,763,922,301,966,840,103,777,310,372,551,77,104,887,448,272,351,545,532,209,939,658,457,712,866,246,32,990,257,694,531,749,809,454,402,127,646,505,905,956,229,808,386,685,696,834,957,399,731,841,609,670,499,66,734,718,664,766,60,273,812,591,22,621,45,425,100,691,282,5,0,512,814,386,197,862,572,507,613,303,348,574,325,200,992,59,918,656,825,978,929,637,921,304,610,967,81,710,10,363,68,362,227,234,748,777,448,672,284,61,975,984,636,300,184,628,711,102,637,536,432,918,525,354,222,135,673,303,198,35,19,618,398,598,852,498,375,300,171,11,713,498,996,701,151,180,682,862,635,671,751,419,589,276,125,812,764,150,467,962,186,838,580,936,437,784,434,164,84,957,176,149,456,172,203,959,704,885,821,691,556,572,111,145,201,588,309,965,739,129,279,925,967,859,213,756,995,999,921,79,957,97,580,765,621,783,724,325,668,897,17,576,822,480,74,23,68,383,988,807,512,267,84,832,478,297,588,473,297,509,904,606,958,484,723,579,620,799,905,640,48,274,217,870,754,643,893,174,26,233,982,891,852,418,75,330,716,663,155,365,525,59,323,483,896,46,63,868,845,320,508,245,594,77,116,700,720,361,874,99,947,208,990,799,627,65,482,695,80,637,60,605,49,735,89,297,781,152,165,978,824,25,223,770,103,691,470,823,53,696,922,352,905,264,151,884,681,985,579,762,975,991,367,376,726,808,673,859,960,190,
                837,136,215,412,906,318,456,728,494,861,425,416,213,682,681,364,566,362,702,145,476,677,136,844,53,214,652};
        System.out.println(new MaximumAverage2().findMaxAverage(arr,398));
    }

}

/*

We keep guessing whether the mid value (average of max and min) can be satisfied in a given sequence, that is to say whether there is an average of greater than
or equal to the interval of k is greater than or equal to the mid value, and if so, we will change the lower limit min to mid, and vice versa, change the max, and do so,
until the guess range is less than 0.00001 you can directly give the answer (the title says that there is an error tolerance range, small hint it).

 O(nlog(max_val-min_val))
 check takes O(n) time and it is executed O(log(max_val-min_val)) times.

 */