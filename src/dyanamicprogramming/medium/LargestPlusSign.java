/*

In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0.
 What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.

An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up, down, left, and right, and made of 1s.
 This is demonstrated in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign
 is checked for 1s.

Examples of Axis-Aligned Plus Signs of Order k:

Order 1:
000
010
000

Order 2:
00000
00100
01110
00100
00000

Order 3:
0000000
0001000
0001000
0111110
0001000
0001000
0000000
Example 1:

Input: N = 5, mines = [[4, 2]]
Output: 2
Explanation:
11111
11111
11111
11111
11011
In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
Example 2:

Input: N = 2, mines = []
Output: 1
Explanation:
There is no plus sign of order 2, but there is of order 1.
Example 3:

Input: N = 1, mines = [[0, 0]]
Output: 0
Explanation:
There is no plus sign, so return 0.

 */
package dyanamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by poorvank.b on 05/03/18.
 */
public class LargestPlusSign {


    public int orderOfLargestPlusSign(int N, int[][] mines) {

        if (N == 0) {
            return 0;
        } else if (mines == null || mines.length == 0) {
            return N - 1;
        }

        int[][] matrix = new int[N][N];
        for (int[] mat : matrix) {
            Arrays.fill(mat,1);
        }

        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]]=0;
        }

        int[][] left = new int[N][N];
        int[][] right = new int[N][N];
        int[][] bottom = new int[N][N];
        int[][] top = new int[N][N];

        int ans=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(matrix[i][j]==1){
                    top[i][j] = (i-1>=0)?top[i-1][j]+1:1;
                    left[i][j] = (j-1>=0)?left[i][j-1]+1:1;
                }
                else{
                    left[i][j] = 0;
                    top[i][j] = 0;
                }
            }
        }


        for(int i=N-1;i>=0;i--){
            for(int j=N-1;j>=0;j--){
                if(matrix[i][j]==1){
                    bottom[i][j] = (i+1<N)?bottom[i+1][j]+1:1;
                    right[i][j] = (j+1<N)?right[i][j+1]+1:1;
                }
                else{
                    bottom[i][j]=0;
                    right[i][j]=0;
                }
                ans = Math.max(ans, Math.min(Math.min(left[i][j],bottom[i][j]), Math.min(right[i][j],top[i][j])));
            }
        }
        return ans;


    }

}
/*

Created 4 matrices for storing largest streak of ones on left, right top and bottom. Finally, take the min of all four directions.
The maximum of these minimums is the answer.

O(n^2) time and space

 */