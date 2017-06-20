/*

We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

 */

package hashing.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 21/05/17.
 */
public class HarmonicSequence {

    public int findLHS(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;

        for ( int number : nums ) {
            Integer count = map.get(number);
            if (null == count) {
                map.put(number, 1);
            } else {
                map.put(number, count + 1);
            }
        }

        for ( Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            int number = entry.getKey();


            Integer c = map.get(number + 1);
            if (null != c) {
                count += c;
            } else {
                count = 0;
            }


            if ( count > maxCount ) {
                maxCount = count;
            }
        }

        return maxCount;

    }

    public static void main(String[] args) {
        int[] arr = {6,10,10,7,8,10,10,8};
        System.out.println(new HarmonicSequence().findLHS(arr));
    }

}
