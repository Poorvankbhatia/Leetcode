/*

We are given hours, a list of the number of hours worked per day for a given employee.

A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.



Example 1:

Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].


Constraints:

1 <= hours.length <= 10000
0 <= hours[i] <= 16

 */
package arrays.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestWIP {

    public int longestWPI(int[] hours) {
        int n = hours.length;
        Map<Integer,Integer> map = new HashMap<>();
        int sum=0;
        int len=0;
        for(int i=0;i<n;i++) {
            int h = hours[i];
            sum+=(h>8?1:-1); // transform each hour to 1 or -1
            map.putIfAbsent(sum,i);
            if(sum>0) { // in hours[0:i], more 1s than -1s
                len = Math.max(len,i+1);
            }
            if(map.containsKey(sum-1)) { // get the index j where sum of hours[0:j] is sum - 1, so that sum of hours[j+1:i] is 1
                len = Math.max(len,i-map.get(sum-1));
            }
        }
        return len;
    }

}
