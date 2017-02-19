/*

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

 */
package arrays.medium;

import java.util.HashMap;

/**
 * Created by poorvank on 19/02/17.
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {

        if(null==nums || nums.length==0 || nums.length==1) {
            return 0;
        }
        int n = nums.length;
        int[] tempArray = new int[n];
        for (int i=0;i<n;i++) {
            if(nums[i]==0) {
                tempArray[i] = -1;
            } else {
                tempArray[i] = 1;
            }
        }

        return findLargestSubArrayWithZeroSum(tempArray,n);


    }

    private int findLargestSubArrayWithZeroSum(int[] arr,int n) {

        int maxLength=0;
        int startIndex=0;
        HashMap<Integer,Integer> sumMap = new HashMap<>();
        int sum=0;

        for (int i=0;i<n;i++) {

            sum += arr[i];

            if(arr[i]==0) {
                if(maxLength==0) {
                    maxLength=1;
                }
            } else if(sum==0) {
                maxLength = Math.max(maxLength,i+1);
            } else if(sumMap.get(sum)!=null) {
                startIndex = sumMap.get(sum);
                maxLength = Math.max(maxLength,(i-startIndex));
            }

            if(!sumMap.containsKey(sum)) {
                sumMap.put(sum,i);
            }

        }

        return maxLength;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,0,1,1,0};
        System.out.print(new ContiguousArray().findMaxLength(arr));
    }

}

/*

Consider all 0 values as -1. The problem now reduces to find out the maximum length subarray with sum = 0.

 */