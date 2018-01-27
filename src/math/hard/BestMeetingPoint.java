/*

A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1,
where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance,
where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

 */

package math.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 27/01/18.
 */
public class BestMeetingPoint {

    public int minTotalDistance(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;


        List<Integer> I = new ArrayList<Integer>();
        List<Integer> J = new ArrayList<Integer>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i ++) {
                if (grid[i][j] == 1) {
                    J.add(j);
                }
            }
        }
        return minTotalDistance(I) + minTotalDistance(J);

    }

    private int minTotalDistance(List<Integer> list) {
        int sum=0;

        for(Integer i: list){
            sum += Math.abs(i - list.get(list.size()/2));
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,0,0,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        System.out.println(new BestMeetingPoint().minTotalDistance(grid));
    }

}

/*


O(mn)

The theory behind (why the median works)

Why the median could minimize the total manhattan distance.

1. Let's start from the 1-dimension case

Suppose we have n people living on a straight street and they want to find somewhere to meet. The total distance is

Σ|x(i)-m|

where enter image description here is the location of each house and enter image description here
is the meeting point. To minimize this problem, take the derivative of this equation. Each term will give you

1, if enter image description here
-1, if enter image description here
To reach the minimum, the derivative must be 0. To make the derivative 0, the number of 1s and -1s must equal.

If n is even, then m must be located between the middle two locations (Any locations between them will give you the minimum, not necessarily the median).
If n is odd, then m must be located on the middle one house. That's the median.

2. Then we can discuss the 2-dimension case

Let's write down the equation directly.

Σ|x(i)-m|+|y(i)-n|


So this time, we have two variables - m and n. Recall what you've learned in multiple variables calculus. To find the minimum, we need to take the
partial derivatives
for the equation. And each partial derivative (or we can say, each dimension) will give you the same result as the 1-D case.


https://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations

 */