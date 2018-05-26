/*

Assume you have an array of length n initialized with all 0's and are given k update operations.
Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex]
(startIndex and endIndex inclusive) with inc.
Return the modified array after all k operations were executed.
Example:
Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Explanation:
Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]

 */
package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank on 20/08/16.
 */
public class RangeAddition {

    public static void main(String[] args) {
        int[][] updates = new int[][] {
                {1,3,2},
                {2,4,3},
                {0,2,-2}
        };

        int length = 5;

        int[] result = new int[5];

        /*
             We don't need to update all the values of the subarray.
             Just update the first value & the value after the last value , to mark the end of range.
         */
        for (int[] update : updates) {
            result[update[0]] += update[2];
            if (update[1] < length - 1) {
                result[update[1] + 1] -= update[2];
            }
        }

        int c=0;
        for (int i=0;i<length;i++) {
            c += result[i];
            result[i] = c;
        }

        System.out.println(Arrays.toString(result));
    }

}

/*

Time Complexity : O(n+k)

 */