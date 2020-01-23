/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].




 */
package arrays.Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by poorvank on 01/12/16.
 */


public class MergeIntervals {

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

    public List<Interval> merge(List<Interval> intervals) {

        List<Interval> result = new ArrayList<>();

        if (intervals.size() == 0 || intervals.size()==1) {
            return intervals;
        }

        intervals.sort(Comparator.comparingInt(o -> o.start));

        result.add(0,intervals.get(0));
        int k=0;

        for (int i=1;i<intervals.size();i++) {
            //Compare current with the latest interval present in result
            if (intervals.get(i).start<=result.get(k).end) {
                Interval merge = new Interval(result.get(k).start,intervals.get(i).end>result.get(k).end?
                                                    intervals.get(i).end:result.get(k).end);

                //Remove old and merge
                result.remove(k);
                result.add(k,merge);
            } else {
                //Add the new one.
                k++;
                result.add(intervals.get(i));
            }
        }


        return result;

    }

    public static void main(String[] args) {
        Interval a = new Interval(1,2);
        Interval b = new Interval(3,5);
        Interval c = new Interval(6,7);
        Interval d = new Interval(8,10);
        Interval e = new Interval(12,16);
        Interval f = new Interval(4,9);
        List<Interval> list = new ArrayList<Interval>(){{
            add(e);
            add(a);
            add(b);
            add(c);
            add(d);
            add(f);
        }};

        System.out.print(new MergeIntervals().merge(list));


    }

}


/*

Same for question Insert Interval:

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<Interval>();
    for (Interval i : intervals) {
        if (newInterval == null || i.end < newInterval.start)
            result.add(i);
        else if (i.start > newInterval.end) {
            result.add(newInterval);
            result.add(i);
            newInterval = null;
        } else {
            newInterval.start = Math.min(newInterval.start, i.start);
            newInterval.end = Math.max(newInterval.end, i.end);
        }
    }
    if (newInterval != null)
        result.add(newInterval);
    return result;
}


 */

