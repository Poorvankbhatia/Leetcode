/*

Given an integer array, you need to find one continuous subarray that if you
only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

 */

package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank on 14/05/17.
 */
public class ShortestUnsorted {

    public int findUnsortedSubarray(int[] nums) {
        if(nums == null) return 0;
        if(nums.length<=1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int rightMost = -2; // The most right element having greater elements on the left side.
        //iterate from beginning of array
        //find the last element which is smaller than the last seen max from
        //its left side and mark it as end
        for(int i = 0; i < nums.length; i ++){
            max = Math.max(max, nums[i]);
            if(nums[i] < max)
                rightMost = i;
        }

        int min = Integer.MAX_VALUE;
        int leftMost = -1; // The most left element having smaller elements on the right side.
        //iterate from end of array
        //find the last element which is bigger than the last seen min from
        //its right side and mark it as begin
        for(int i = nums.length - 1; i >= 0; i --){
            min = Math.min(min, nums[i]);
            if(nums[i] > min)
                leftMost = i;
        }

        return rightMost - leftMost + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,3,3};
        System.out.println(new ShortestUnsorted().findUnsortedSubarray(arr));
    }

}

/*

SORT Solution:
public int findUnsortedSubarray(int[] array) {

        int[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);

        int start = 0,end=0;

        for(int i=0;i<copy.length;i++) {

            if(array[i]!=copy[i]) {
                start=i;
                break;
            }

        }

        for (int j=copy.length-1;j>=0;j--) {
            if(array[j]!=copy[j]) {
                end=j;
                break;
            }
        }

        return end!=start?end-start+1:0;

    }

 */