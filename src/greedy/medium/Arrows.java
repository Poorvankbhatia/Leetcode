/*

There are a number of spherical balloons spread in two-dimensional space.
For each balloon, provided input is the start and end coordinates of the horizontal diameter.
Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice.
Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts
by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot
keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11
(bursting the other two balloons)

 */
package greedy.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 29/11/16.
 */
public class Arrows {

    private class Balloon implements Comparable<Balloon> {
        int start;
        int end;

        public Balloon(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Balloon balloon) {
            if(this.end>balloon.end) {
                return 1;
            } else if(this.end<balloon.end) {
                return -1;
            } else {
                if(this.start>balloon.start) {
                    return 1;
                } else if(this.start<balloon.start) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }

    public int findMinArrowShots(int[][] points) {

        int rows = points.length;

        if(rows==0){
            return 0;
        }

        Balloon[] balloons = new Balloon[rows];

        for (int i=0;i<rows;i++) {
            balloons[i] = new Balloon(points[i][0],points[i][1]);
        }

        Arrays.sort(balloons);

        int minArrows = 1;
        int end = balloons[0].end;

        for (int i=1;i<balloons.length;i++) {
            if(balloons[i].start<=end) {
                continue;
            } else {
                minArrows++;
                end = balloons[i].end;
            }
        }

        return minArrows;

    }

}

/*

1.Sort balloon diameter by ending points
2.Only count valid intervals we need, and skip overlapping intervals
return the count

 */

