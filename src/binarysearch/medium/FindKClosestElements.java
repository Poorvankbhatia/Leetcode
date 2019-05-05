/*

Given a sorted array, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104


 */
package binarysearch.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by poorvank.b on 02/11/17.
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> result = new ArrayList<>();
        int count=0;

        int l= getCrossOverIndex(arr,x,0,arr.length-1),r=l+1;

        if (arr[l] == x) {
            result.add(x);
            count++;
        }

        while (l >= 0 && r < arr.length && count < k) {

            if (Math.abs(x - arr[l]) <= Math.abs(arr[r] - x)) {
                result.add(arr[l--]);
            } else {
                result.add(arr[r++]);
            }
            count++;

        }

        // If there are no more elements on right side, then
        // print left elements
        while (count < k && l >= 0) {
            result.add(arr[l--]);
            count++;
        }

        // If there are no more elements on left side, then
        // print right elements
        while (count < k && r < arr.length) {
            result.add(arr[r++]);
            count++;
        }

        Collections.sort(result);
        return result;
    }

    private int getCrossOverIndex(int[] arr, int x, int low, int high) {

        if(x>=arr[high]) {
            return high;
        }

        if(x<=arr[low]) {
            return low;
        }

        int mid = low+(high-low)/2;

        if (arr[mid] <= x && arr[mid + 1] > x) {
            return mid;
        }

        else if (arr[mid] < x) {
            return getCrossOverIndex(arr, x, mid + 1, high);
        }

        return getCrossOverIndex(arr, x, low, mid - 1);

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,2,2,3,4,6,7,8,9,9};
        System.out.println(new FindKClosestElements().findClosestElements(arr,4,5));
    }

}
