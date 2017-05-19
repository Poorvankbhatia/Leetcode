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

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,3,3};
        System.out.println(new ShortestUnsorted().findUnsortedSubarray(arr));
    }

}
