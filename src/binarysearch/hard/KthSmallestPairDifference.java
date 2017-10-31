/*

Given an integer array, return the k-th smallest distance among all the pairs.
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.

 */
package binarysearch.hard;

import java.util.Arrays;

/**
 * Created by poorvank.b on 31/10/17.
 */
public class KthSmallestPairDifference {

    public int smallestDistancePair(int[] nums, int k) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        int n = nums.length;
        Arrays.sort(nums);

        int lo = nums[1]-nums[0];
        for (int i=2;i<n;i++) {
            lo = Math.min(nums[i]-nums[i-1],lo);
        }

        int hi = nums[nums.length-1]-nums[0];

        while (lo<hi) {
            int mid = lo+(hi-lo)/2;
            int count = countValues(nums,mid);
            if(count<k) {
                lo=mid+1;
            } else {
                hi = mid;
            }
        }


        return lo;
    }

    private int countValues(int[] nums,int target) {

        int n = nums.length;
        int count = 0;
        for (int i=0;i<n;i++) {
            int j=i;
            while (j<n && nums[j]-nums[i]<=target) {
                j++;
            }
            count+=j-i-1;
        }

        return count;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,5,9,13};
        System.out.println(new KthSmallestPairDifference().smallestDistancePair(arr,4));
    }

}

/*

O(NlogW+NlogN), where N is the length of nums, and W is equal to nums[nums.length - 1] - nums[0]

 */