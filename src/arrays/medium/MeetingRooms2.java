/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

 */
package arrays.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 18/02/17.
 */
public class MeetingRooms2 {

    public class Interval {
        int start;
        int end;
    }

    public int minMeetingRooms(Interval[] intervals) {
        int result = 0;
        int meetingRooms=0;
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        int i=0;
        for (Interval interval : intervals) {
            start[i] = interval.start;
            end[i] = interval.end;
            i++;
        }

        // 0,5,10    10,15,20
        Arrays.sort(start);
        Arrays.sort(end);

        i=0;int j=0;

        while (i<intervals.length && j<intervals.length) {
            if(end[j]>start[i]) {
                meetingRooms++;
                result=Math.max(result,meetingRooms);
                i++;
            } else {
                meetingRooms--;
                j++;
            }
        }

        return result;

    }

}

/*


Alternate method:

public int minMeetingRooms(Interval[] intervals) {
    if(intervals==null||intervals.length==0)
        return 0;

    Arrays.sort(intervals, new Comparator<Interval>(){
        public int compare(Interval i1, Interval i2){
            return i1.start-i2.start;
        }
    });

    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    int count=1;
    queue.offer(intervals[0].end);

    for(int i=1; i<intervals.length; i++){
        if(intervals[i].start<queue.peek()){
            count++;

        }else{
            queue.poll();
        }

        queue.offer(intervals[i].end);
    }

    return count;
}

 */