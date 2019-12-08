/*

Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all
the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

It is guaranteed that there will be an answer.



Example 1:

Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
Example 2:

Input: nums = [2,3,5,7,11], threshold = 11
Output: 3
Example 3:

Input: nums = [19], threshold = 5
Output: 4


Constraints:

1 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^6
nums.length <= threshold <= 10^6

 */
package binarysearch.medium;

public class SmallestDivisorGivenThreshold {

    public int smallestDivisor(int[] nums, int threshold) {
        int max = 1000000;
        int min = 1;
        while(min<max) {
            int mid = min+(max-min)/2;
            if(sumOfDivision(nums,mid)<=threshold) { // denominator is more, i.e mid is greater than solution hence reduce it so reduce max.
                max=mid;
            } else {
                min=mid+1;
            }
        }
        return min;
    }

    private int sumOfDivision(int[] nums,int mid) {
        int v=0;
        for (int n : nums) {
            v += (int) Math.ceil((double)n/mid);
        }
        return v;
    }

    public static void main(String[] args) {
        System.out.println(new SmallestDivisorGivenThreshold().smallestDivisor(new int[]{2,3,5,7,11},11));
    }

}
