/*

Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2].
(coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:
rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.


Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.


Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.

 */


package arrays.Hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 07/05/17.
 */
public class PerfectRectangle {

    public boolean isRectangleCover(int[][] rectangles) {

        int minX=Integer.MAX_VALUE,minY = Integer.MAX_VALUE,maxX = Integer.MIN_VALUE,maxY = Integer.MIN_VALUE;

        Set<String> set = new HashSet<>();

        for (int[] rectangle : rectangles) {
            if(minX>rectangle[0]) {
                minX = rectangle[0];
            }
            if(minY>rectangle[1]) {
                minY = rectangle[1];
            }
            if(maxX<rectangle[2]) {
                maxX = rectangle[2];
            }
            if(maxY<rectangle[3]) {
                maxY = rectangle[3];
            }

            String s1 = rectangle[0]+"-"+rectangle[1];
            String s2 = rectangle[0]+"-"+rectangle[3];
            String s3 = rectangle[2]+"-"+rectangle[3];
            String s4 = rectangle[2]+"-"+rectangle[1];

            // Remove the redundant points.
            if (!set.add(s1))
                set.remove(s1);
            if (!set.add(s2))
                set.remove(s2);
            if (!set.add(s3))
                set.remove(s3);
            if (!set.add(s4))
                set.remove(s4);

        }

        //count of all the points should be even, and that of all the four corner points should be one, draw a diagram to understand
        if(set.size()!=4 || !set.contains(minX+"-"+minY) || !set.contains(minX+"-"+maxY) || !set.contains(maxX+"-"+minY) || !set.contains(maxX+"-"+maxY)) {
            return false;
        }

        long rectangleCoverArea = (maxX-minX)*(maxY-minY);

        long sumOfRectangles=0;

        for (int[] rectangle : rectangles) {
            sumOfRectangles += (rectangle[2]-rectangle[0])*(rectangle[3]-rectangle[1]);
        }



        System.out.println(rectangleCoverArea + " " + sumOfRectangles);



        //the overall rectangle area should be equal to the sum of small rectangles
        return sumOfRectangles==rectangleCoverArea;

    }

    public static void main(String[] args) {

        int[][] rectangles = {
                {0, 0, 1, 1},
                {0, 1, 3, 2},
                {1, 0, 2, 2}
        };

        System.out.println(new PerfectRectangle().isRectangleCover(rectangles));

    }

}
