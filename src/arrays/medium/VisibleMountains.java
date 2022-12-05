/*
You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi] states that mountain i has a peak at coordinates (xi, yi). A mountain can be described as a right-angled isosceles triangle, with its base along the x-axis and a right angle at its peak. More formally, the gradients of ascending and descending the mountain are 1 and -1 respectively.

A mountain is considered visible if its peak does not lie within another mountain (including the border of other mountains).

Return the number of visible mountains.

Input: peaks = [[2,2],[6,3],[5,4]]
Output: 2
Explanation: The diagram above shows the mountains.
- Mountain 0 is visible since its peak does not lie within another mountain or its sides.
- Mountain 1 is not visible since its peak lies within the side of mountain 2.
- Mountain 2 is visible since its peak does not lie within another mountain or its sides.
There are 2 mountains that are visible.

Input: peaks = [[1,3],[1,3]]
Output: 0
Explanation: The diagram above shows the mountains (they completely overlap).
Both mountains are not visible since their peaks lie within each other.


Constraints:

1 <= peaks.length <= 105
peaks[i].length == 2
1 <= xi, yi <= 105


 */
package arrays.medium;

import java.util.Arrays;

public class VisibleMountains {

    public int visibleMountains(int [][] peaks){
        for(int i = 0; i < peaks.length; i ++){
            int temp = peaks[i][0];
            peaks[i][0] -= peaks[i][1];
            peaks[i][1] += temp;
        }
        Arrays.sort(peaks, (a, b) -> {
            if(a[0] - b[0] != 0){
                return a[0] - b[0];
            }
            else{
                return a[1] - b[1];
            }
        });
        int size = 0;
        int i = 0;
        while(i < peaks.length){
            size ++;
            if(i + 1 == peaks.length){
                return size;
            }
            int currEnd = peaks[i][1];
            if(peaks[i][0] == peaks[i + 1][0]){
                size --;
            }
            while(i + 1 != peaks.length && currEnd >= peaks[i + 1][1]){
                i ++;
            }
            i ++;
        }
        return size;
    }

}

/*

Since we know the slopes of all mountains must be 1 going uphill and -1 going downhill,
we can convert each peak to a start and end. We then sort all mountains by earliest
start(breaking ties by earliest end). Now we go through our mountains in sorted order.
We initially assume the mountain we're considering is visible and increment size by 1.
If this is the last mountain, we're done. If not, we check if the next mountain has the
same start as our current mountain. If it does, our current mountain cannot be visible
since the next mountain is guaranteed to finish at least as late as our current mountain
does and we decrement size by 1. Now, we want to skip all mountains we know cannot be visible,
namely those that end at or before our current end. After we have skipped all possible mountains,
 we continue to our next iteration on the next unconsidered mountain).
 Finally, after considering all mountains we return our size.



Another:

Explanation
Observation: If one point A is within point B, then the entire triangle (mountain) of point A is within the triangle of point B
Below is an implementation of the hint section
Get rid of all repeat points as they won't be visible
Sort by x-axis
Once x-axis is sorted, we will use mono-stack to keep track of visible points. One point is visible if it's not covered by the last point in the mono-stack
If current point is visible
Pop all previous points that are within the current point
Append current point to the mono-stack
If current point is not visible, ignore it
See below scenarios
Scenario 1 (previous points are within the current point):
peaks = [[2,1], [3, 1], [4, 36]], note that the first 2 points are within the last point; for the mono-stack, we will only keep the last point
stack = [[2,1]]
stack = [[2,1], [3, 1]]
stack = [[4, 36]] because first two points are within [4, 36], thus they've been popped out.
Scenario 2 (current point is not visible):
peaks = [[2,10], [3, 1]], note that the 2nd point will not be included to the stack since it's within the first point
stack = [[2,10]]
stack = [[2, 10]], since [3,1] will be ignored, as it's within [2, 10]
Otherwise, we will append the current point to the mono-stack
Time: O(nlgn), n = len(peaks) due to the sorting algorithm


 */