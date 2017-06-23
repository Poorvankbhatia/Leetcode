/*

Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays
(each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|.
Your task is to find the maximum distance.

Example 1:
Input:
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation:
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].


 */

package arrays.easy;

import java.util.List;

/**
 * Created by poorvank on 22/06/17.
 */
public class MaximumDistance {

    public int maxDistance(List<List<Integer>> arrays) {
        int length = arrays.get(0).size();
        int max = arrays.get(0).get(length-1),min=arrays.get(0).get(0);
        int result=0;
        for(int i=1;i<arrays.size();i++) {
            List<Integer> list = arrays.get(i);
            int currentMax= list.get(list.size()-1);
            int currentMin = list.get(0);
            // Since the arrays have to be different calculate current Max and min.
            int currentResult = Math.max(currentMax-min,Math.abs(currentMin-max));
            max = Math.max(max, currentMax);
            min = Math.min(min, currentMin);
            result = Math.max(result,currentResult);


        }

        return result;
    }

}
