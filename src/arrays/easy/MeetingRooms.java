/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

 */

package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank on 18/02/17.
 */
public class MeetingRooms {

    public class Interval {
        int start;
        int end;
    }

    public boolean canAttendMeetings(Interval[] intervals) {

        if(null==intervals || intervals.length==0) {
            return false;
        }
        Arrays.sort(intervals, (o1, o2) -> o1.start-o2.start);
        for (int i=1;i<intervals.length;i++) {
            if(intervals[i].start<intervals[i-1].end) {
                return false;
            }
        }

        return true;

    }

}
