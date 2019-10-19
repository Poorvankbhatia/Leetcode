/*

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
 return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other.
That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []


Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeetingScheduler {

    public List<Integer> minAvailableDuration(int[][] A, int[][] B, int duration) {
        Arrays.sort(A, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(B, Comparator.comparingInt(a -> a[0]));

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

        List<Integer> result = new ArrayList<>();
        for(int[] e : list) {
            if(e[1] - e[0] >= duration) {
                result.add(e[0]);
                result.add(e[0]+duration);
                break;
            }
        }

        return result;

    }

    private int[] intersection(int[] A,int[] B) {
        return new int[]{Math.max(A[0],B[0]),Math.min(A[1],B[1])};
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{
                {0,2}
        };
        int[][] B = new int[][]{
                {1,3}
        };
        new MeetingScheduler().minAvailableDuration(A,B,1);
    }

}
