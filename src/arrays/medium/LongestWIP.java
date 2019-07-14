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

public class LongestWIP {

    public int longestWPI(int[] hours) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        int maxInterval = 0;

        for(int i = 0; i < hours.length; i++) {
            sum = hours[i] > 8 ? sum + 1 : sum - 1;

            if(sum >= 1) {
                maxInterval = Math.max(maxInterval, i + 1);
            }

            if(map.get(sum)==null) {
                map.put(sum, i);
            } else {
                if(map.get(sum - 1)!=null)
                    maxInterval = Math.max(maxInterval, i - map.get(sum - 1));
            }
        }

        if(maxInterval == 0) {
            for (int hour : hours) {
                if (hour > 8) {
                    maxInterval = 1;
                    break;
                }
            }
        }

        return maxInterval;
    }

}
