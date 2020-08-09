/*
Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

Return the array in the form [x1,y1,x2,y2,...,xn,yn].



Example 1:

Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7]
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
Example 2:

Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]
Example 3:

Input: nums = [1,1,2,2], n = 2
Output: [1,2,1,2]


Constraints:

1 <= n <= 500
nums.length == 2n
1 <= nums[i] <= 10^3
 */
package arrays.easy;

public class ShuffleTheArray {

    public int[] shuffle(int[] nums, int n) {
        int i=0,j=n,k=0;
        int[] ans = new int[nums.length];
        while(j<nums.length && i<n) {
            ans[k++]=nums[i++];
            ans[k++]=nums[j++];
        }
        return ans;
    }

}

/*

Better Method: O(1) space.

Two rules:
ith element -> 2ith element in new array
i + n th element -> 2i + 1 th element in the new array

Adjust the array backwards,
For each step, consider ith element
Before the ith element, the array is ordered by original order
After the ith element, the array is ordered by new order
For the ith element, find the proper element using the two rules

public int[] shuffle(int[] nums, int n) {
        for (int i = nums.length-1; i>0; i--) {
            int k = i;
            do {
                if (k % 2 == 1)
                    k = k/2 + n;
                else
                    k = k/2;
            } while (k > i);
            swap(i, k, nums);
        }
        return nums;
    }

    private void swap(int i,int k,int[] nums) {
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
    }
 */
