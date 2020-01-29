/*

Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.



Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,...], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation:
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.


Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8

 */
package binarysearch.medium;

public class MissingElementSortedArray {

    public int missingElement(int[] nums, int k) {

        // assume nums is not empty
        int left = 0;
        int right = nums.length-1;
        int count = getMissingCount(nums, right);
        if(k > count) {
            return nums[right] + k - count;
        }
        while(left < right) {
            int mid = left + (right - left) /2 ;
            count = getMissingCount(nums, mid);
            if(count >= k){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left-1] + k - getMissingCount(nums, left-1);
    }

    /*Total range of numbers present - n */
    private int getMissingCount(int[] nums, int index){
        return nums[index] - nums[0] - index;
    }

    public static void main(String[] args) {
        System.out.println(new MissingElementSortedArray().missingElement(new int[]{4,7,9,10,13,15,16,18,19},4));
    }

}