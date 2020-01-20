/*

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */
package arrays.Hard;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        list.sort((a,b)->(a[0]!=b[0])?a[0]-b[0]:b[1]-a[1]);
        List<int[]> mergedList = new ArrayList<>();
        mergedList.add(list.get(0));
        int k=0;
        for(int i=1;i<list.size();i++) {
            if(list.get(i)[0]<=mergedList.get(k)[1]) {
                int[] merge = new int[]{list.get(k)[0],Math.max(mergedList.get(k)[1],list.get(i)[1])};
                mergedList.remove(k);
                mergedList.add(k,merge);
            } else {
                mergedList.add(list.get(i));
                k++;
            }
        }
        int[][] result = new int[mergedList.size()][2];
        k=0;
        for(int[] interval : mergedList) {
            result[k++] = interval;
        }
        return result;
    }

}
