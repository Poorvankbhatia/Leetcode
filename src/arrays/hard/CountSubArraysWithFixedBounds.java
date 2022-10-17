/*

You are given an integer array nums and two integers minK and maxK.

A fixed-bound subarray of nums is a subarray that satisfies the following conditions:

The minimum value in the subarray is equal to minK.
The maximum value in the subarray is equal to maxK.
Return the number of fixed-bound subarrays.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
Output: 2
Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
Example 2:

Input: nums = [1,1,1,1], minK = 1, maxK = 1
Output: 10
Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.


Constraints:

2 <= nums.length <= 105
1 <= nums[i], minK, maxK <= 106
 */

package arrays.hard;

public class CountSubArraysWithFixedBounds {

    public long countSubarrays(int[] nums, int minK, int maxK) {

        long c1 = count(nums,maxK,minK);
        long c2 = count(nums,maxK,minK+1);
        long c3 = count(nums,maxK-1, minK);
        long c4 = count(nums,maxK-1,minK+1);
        return c1-c2-c3+c4;
    }


    private long count(int[] arr, int r, int l) {
        long ans = 0;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] > r || arr[i]< l) {
                i++;
                continue;
            }
            long count = 0;
            while (i < arr.length && (arr[i] <= r && arr[i] >= l)) {
                i++;
                count++;
            }
            ans += ((count * (count + 1)) / 2);
        }
        return ans;
    }

}

/**
 we'll calculate all the possible subarrays the fall in the window where max and min are between a certain window.

 So,
 count _1 will store the subarrays are in the window >=minK and <=maxK.
 count _2 will store the subarrays are in the window >minK and <=maxK.
 count _3 will store the subarrays are in the window >=minK and <maxK.
 count _4 will store the subarrays are in the window >minK and <maxK.

 see image

 To calculate the number of subarrays that have min=minK and max=maxK, we use count_1 - count_2 - count_3 + count_4.

 count_4 is beacause this window is present in both count_2 and count_3, and is subtracted 2 times.
 Hence it has to be added 1 time to get the correct answer.

 */