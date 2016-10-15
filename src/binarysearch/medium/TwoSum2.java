/*

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

 */
package binarysearch.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 11/10/16.
 */
public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {

        int[] result = new int[2];

        for (int i=0;i<numbers.length;i++) {
            int x = target-numbers[i];
            if(!(x<0)) {
                x = binarySearch(i+1,numbers.length-1,numbers,x);
                if(x!=-1) {
                    result[0] = i+1;
                    result[1] = x+1;
                }
            }
        }

        return result;

    }

    private int binarySearch(int start,int end,int[] arr,int target) {

        if (end>=start) {

            int mid = start + (end-start)/2;

            if(arr[mid]==target) {
                return mid;
            }
            else if(arr[mid]<target) {
                return binarySearch(mid+1,end,arr,target);
            } else {
                return binarySearch(start,mid-1,arr,target);
            }

        }

        return -1;

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new TwoSum2().twoSum(new int[]{2, 3,4},6)));

    }

}
