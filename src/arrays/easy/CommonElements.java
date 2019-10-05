/*

Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.



Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.


Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000

 */
package arrays.easy;

import java.util.ArrayList;
import java.util.List;

public class CommonElements {

    public List<Integer> arraysIntersection(int[] ar1, int[] ar2, int[] ar3) {
        List<Integer> list = new ArrayList<>();
        // Initialize starting indexes for ar1[], ar2[] and ar3[]
        int i = 0, j = 0, k = 0;

        // Iterate through three arrays while all arrays have elements
        while (i < ar1.length && j < ar2.length && k < ar3.length)
        {
            // If x = y and y = z, print any of them and move ahead
            // in all arrays
            if (ar1[i] == ar2[j] && ar2[j] == ar3[k])
            {   list.add(ar1[i]); i++;j++;k++;}

            // x < y
            else if (ar1[i] < ar2[j])
                i++;

                // y < z
            else if (ar2[j] < ar3[k])
                j++;

                // We reach here when x > y and z < y, i.e., z is smallest
            else
                k++;
        }

        return list;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,4,5};
        int[] arr2 = new int[]{1,2,5,7,9};
        int[] arr3 = new int[]{1,3,4,5,8};
        System.out.println(new CommonElements().arraysIntersection(arr1,arr2,arr3));

    }

}
