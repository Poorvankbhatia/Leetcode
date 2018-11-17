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
        int[] timeRevert = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String timePoint = timePoints.get(i);
            String[] timeParts = timePoint.split(":");
            int hour = Integer.valueOf(timeParts[0].trim());
            int minute = Integer.valueOf(timeParts[1].trim());
            int revert = hour * 60 + minute;
            timeRevert[i] = revert;
        }
        Arrays.sort(timeRevert);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < timeRevert.length; i++) {
            if (i < timeRevert.length - 1) {
                if (timeRevert[i + 1] - timeRevert[i] < min) {
                    min = timeRevert[i + 1] - timeRevert[i];
                }
            } else {
                if (timeRevert[0] + 1440 - timeRevert[i] < min) {
                    min = timeRevert[0] + 1440 - timeRevert[i];
                }
            }
        }
        return min;

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
