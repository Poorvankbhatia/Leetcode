/*

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.


 */
package arrays.Hard;

import java.util.*;

/**
 * Created by poorvank.b on 12/01/18.
 */
public class EmployeeFreeTime {

    private class Interval {
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
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> list = new ArrayList<>();
        if(schedule==null || schedule.size()==0) {
            return list;
        }

        List<Interval> intervalList = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                intervalList.add(interval);
            }
        }

        Collections.sort(intervalList, (o1, o2) -> {
            return Integer.compare(o1.start, o2.start);


        });

        int k =0;

        list.add(intervalList.get(0));

        for (int i=1;i<intervalList.size();i++) {
            //Compare current with the latest interval present in result
            if (intervalList.get(i).start<=list.get(k).end) {
                Interval merge = new Interval(list.get(k).start,intervalList.get(i).end>list.get(k).end?
                        intervalList.get(i).end:list.get(k).end);

                //Remove old and merge
                list.remove(k);
                list.add(k,merge);
            } else {
                //Add the new one.
                k++;
                list.add(intervalList.get(i));
            }
        }

        List<Interval> res = new ArrayList<>();

        k=0;
        for (int i=1;i<list.size();i++) {
            Interval interval = new Interval(list.get(k).end,list.get(i).start);
            res.add(interval);
            k=i;
        }

        return res;

    }

}
