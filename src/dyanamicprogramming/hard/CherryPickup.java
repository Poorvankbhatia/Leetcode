/*
In a N x N grid representing a field of cherries, each cell is one of three possible integers.



0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.


Your task is to collect maximum number of cherries possible by following the rules below:



Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.




Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation:
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.


Note:

grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 */
package dyanamicprogramming.hard;

import java.util.Arrays;

public class CherryPickup {

    int[][][] dp;
    public int cherryPickup(int[][] grid) {
        dp = new int[50][50][50];
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b,-1);
            }
        }

        int N = grid.length;
        int val =  helper(0,0,0,N,grid);
        return val<0?0:val;
    }

    private int helper(int r1,int c1,int c2,int N,int[][] grid) {
        int r2 = r1+c1-c2;
        if(r1>=N || r2>=N || c1>=N || c2>=N || grid[r1][c1]==-1 || grid[r2][c2]==-1) {
            return Integer.MIN_VALUE;
        }
        if(dp[r1][c1][c2]!=-1) {
            return dp[r1][c1][c2];
        }
        // if person 1 reached the bottom right, return what's in there (could be 1 or 0)
        if(r1 == N - 1 && c1 == N - 1) {
            return grid[r1][c1];
        }
        // if person 2 reached the bottom right, return what's in there (could be 1 or 0)
        if(r2 == N - 1 && c2 == N - 1) {
            return grid[r2][c2];
        }
        int ans = grid[r1][c1];
        if(c1!=c2) {
            ans+=grid[r2][c2];
        }
        // since each person of the 2 person can move only to the bottom or to the right, then the total number of cherries
        // equals the max of the following possibilities:
        //    P1     |      P2
        //   DOWN    |     DOWN
        //   DOWN    |     RIGHT
        //   RIGHT   |     DOWN
        //   RIGHT   |     RIGHT
        int temp = Math.max(Math.max(helper(r1,c1+1,c2+1,N,grid),helper(r1+1,c1,c2,N,grid)),Math.max(helper(r1,c1+1,c2,N,grid),helper(r1+1,c1,c2+1,N,grid)));
        ans +=temp;
        dp[r1][c1][c2]=ans;
        return ans;
    }

}


/*
It is easy to see:
Instead of having two paths starting from 0,0 and then other path from N,N.
We can have two people starting from 0,0 and find two paths that collects maximum cherries.
First  person finds the path to collect maximum cherries and mark those cherries collected then
Second person finds another path to collect maximum cherries.

Though here is the case where local maximum is not global maximum.
So having cherry pick up by person1 and then person2 won't give the correct result.
This approach fails to find the best answer to this case.

Reference : https://leetcode.com/problems/cherry-pickup/solution/
Approach #1: Greedy [Wrong Answer]
11100
00101
10100
00100
00111
In above example we should be able to pick all cherries.
But, with our approach person1 will collect 9 cherries leaving once that is on the right(1,4) and on the left(2,0).
Then person2 won't be able to collect both cherries he can collect only right one or only left one.

Approach #2:
Now, we know that we want collectively maximum cherries.
So, we have to do the traversal of both paths at the same time and select maximum global answer.
The potential problem of this approach is double counting (if we collect same cherry in 2 paths), but this can be easily avoided in code.
If both are at the same cell we count cherry only once.
Following code is backtracking brute force so it is TLE.
I think Time Complexity is : 4^N*N. As we are calling cherryPickup 4 times recursively with problem size N*N.
 */