
/*

Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle.
Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

Example 1:
Input: grid =
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
Example 2:
Input: grid =
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
Example 3:
Input: grid =
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
Note:
The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.

 */
package backtracking.medium;

/**
 * Created by poorvank.b on 17/12/17.
 */
public class CornerRectangles {

    public int countCornerRectangles(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int result = 0;

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                for (int nextI=i+1;nextI<m && grid[i][j]==1;nextI++) {
                    for (int nextJ=j+1;nextJ<n && grid[nextI][j]==1;nextJ++) {
                           result+=grid[nextI][nextJ]*grid[i][nextJ];
                    }
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,0,1},{1,0,1}};
        System.out.println(new CornerRectangles().countCornerRectangles(arr));
    }

}

/*

identify the possible rectangles, then check their 4 corners for validity into count.

Another method:

public int countCornerRectangles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int np = 0;
                for (int k = 0; k < m; ++k) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) {
                        np ++;
                    }
                }
                ret += np * (np - 1) / 2;
            }
        }
        return ret;
    }

 When a row is filled with 1s, we do work to enumerate every pair of 1s. This is okay when is small, but expensive when is big.

Say the entire top row is filled with 1s. When looking at the next row with say, f 1s that match the top row, the number of rectangles
created is just the number of pairs of 1s, which is f * (f-1) / 2. We could find each f quickly using a Set and a simple linear scan of each row.

Let's call a row to be heavy if it has more than points. The above algorithm changes the complexity of counting a heavy row from to ,
and there are at most heavy rows.

Time Complexity: where is the number of ones in the grid.

Space Complexity: in additional space, for rows, target, and count.

 */