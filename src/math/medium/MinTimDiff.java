/*

Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.

 */

package math.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank.b on 12/03/17.
 */
public class MinTimDiff {

    public int findMinDifference(List<String> timePoints) {
        int timeCount = timePoints.size();
        int sortedTimes[] = new int[timeCount];

        for (int i = 0; i < timeCount; i++) {
            String[] arr = timePoints.get(i).split(":");
            String h = arr[0];
            String s = arr[1];
            int t = Integer.valueOf(h)*60 + Integer.valueOf(s);
            sortedTimes[i] = t;
        }
        Arrays.sort(sortedTimes);/*sort times*/

        /*Number of minutes in a day.*/
        int minDiff = 1440;
        for (int i = 0; i < timeCount; i++) {
            int curr = sortedTimes[i];
            int prev = sortedTimes[(timeCount+i-1)%timeCount];
            //Handle case of (24:00 && 00:00)
            int diffPrev = Math.abs(curr - prev) < 1440 - Math.abs(curr - prev ) ?
                    Math.abs(curr - prev) :  1440 - Math.abs(curr - prev  ) ;
            if (minDiff > diffPrev) {
                minDiff = diffPrev;
            }
        }

        return minDiff;

    }

    public static void main(String[] args) {
        List<String> times = new ArrayList<>();
        times.add("2:30");
        //times.add("00:00");
        times.add("00:12");
        times.add("24:00");
        times.add("23:55");
        System.out.println(new MinTimDiff().findMinDifference(times));
    }

}
