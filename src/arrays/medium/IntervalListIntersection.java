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


    public int[][] intervalIntersection(int[][] A, int[][] B) {

        int i=0,j=0;

        List<int[]> list = new ArrayList<>();
        while(i<A.length && j<B.length) {
            if(A[i][1]<B[j][0]) {
                i++;
            } else if(A[i][0]>B[j][1]) {
                j++;
            }  else {
                list.add(intersection(A[i],B[j]));
                if(A[i][1]<B[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }

        }

        int[][] result = new int[list.size()][2];
        for(i=0;i<result.length;i++) {
            result[i]=list.get(i);
        }

        return result;

    }

    private int[] intersection(int[] A,int[] B) {
        return new int[]{Math.max(A[0],B[0]),Math.min(A[1],B[1])};
    }
}
