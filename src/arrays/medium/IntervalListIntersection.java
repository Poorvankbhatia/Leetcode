/*

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9


 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersection {


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

    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i].end < B[j].start) {
                i++;
            } else if (A[i].start > B[j].end) {
                j++;
            } else {
                list.add(intersect(A[i], B[j]));
                if (A[i].end < B[j].end) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        int n = list.size();
        Interval[] result = new Interval[n];
        for (int k = 0; k < n; k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    private Interval intersect(Interval a, Interval b) {
        return new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end));
    }
}
