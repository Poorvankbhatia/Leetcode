/*

Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.

Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows

a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr


Example 1:

Input: arr = [4,2,1,3]
Output: [[1,2],[2,3],[3,4]]
Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
Example 2:

Input: arr = [1,3,6,10,15]
Output: [[1,3]]
Example 3:

Input: arr = [3,8,-10,23,19,-4,-14,27]
Output: [[-14,-10],[19,23],[23,27]]


Constraints:

2 <= arr.length <= 10^5
-10^6 <= arr[i] <= 10^6

 */
package arrays.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference {

    public List<List<Integer>> minimumAbsDifference(int[] A) {
        List<List<Integer>> result = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        Arrays.sort(A);
        for(int i=0;i<A.length-1;i++)
        {
            if (Math.abs(A[i+1]-A[i]) < minDiff)
            {
                result.clear();
                List<Integer> list = new ArrayList<>();
                list.add(Math.min(A[i],A[i+1]));
                list.add(Math.max(A[i],A[i+1]));
                result.add(list);
                minDiff = Math.abs(A[i+1]-A[i]) ;
            } else if (Math.abs(A[i+1]-A[i]) == minDiff) {
                List<Integer> list = new ArrayList<>();
                list.add(Math.min(A[i],A[i+1]));
                list.add(Math.max(A[i],A[i+1]));
                result.add(list);
            }
        }

        return result;
    }

}
