/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
 */
package greedy.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by poorvank.b on 08/09/17.
 */


public class MeetingRooms2 {
    private static class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }
        public String toString() {
            return start+" "+end;
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals==null||intervals.length==0)
            return 0;
        Arrays.sort(intervals, Comparator.comparingInt(i2 -> i2.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
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

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(4,9),new Interval(5,7),new Interval(6,12)};
        System.out.println(new MeetingRooms2().minMeetingRooms(intervals));
    }

}
