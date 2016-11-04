/*

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

 */
package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 03/11/16.
 */
public class Duplicates {

    public List<Integer> findDuplicates(int[] arr) {

        int i=0;

        while (i<arr.length) {

            if(arr[i]<0) {
                i++;
                continue;
            }

            int elementIndex = arr[i]-1;

            if(arr[elementIndex]>0) {
                arr[i] = arr[elementIndex];

                arr[elementIndex] = -1;
            } else {
                arr[elementIndex]--;
                arr[i] = 0;
                i++;
            }

        }

        for (i=0;i<arr.length;i++) {
            arr[i] = Math.abs(arr[i]);
        }

        List<Integer> list = new ArrayList<>();

        for (i=0;i<arr.length;i++) {
            if(arr[i]>1) {
                list.add(i+1);
            }
        }

        return list;
    }

}
