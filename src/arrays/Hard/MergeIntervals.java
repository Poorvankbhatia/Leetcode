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

class Interval {
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

public class MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {

        List<Interval> result = new ArrayList<>();

        if (intervals.size() == 0 || intervals.size()==1) {
            return intervals;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start>o2.start) {
                    return 1;
                } else if(o1.start<o2.start) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });

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
        Interval a = new Interval(2,3);
        Interval b = new Interval(2,2);
        Interval c = new Interval(3,3);
        Interval d = new Interval(4,6);
        Interval e = new Interval(1,3);
        Interval f = new Interval(5,7);
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


