/*

We have a grid of 1s and 0s; the 1s in a cell represent bricks.
 A brick will not drop if and only if it is directly connected to the top of the grid, or
 at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j),
the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input:
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation:
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input:
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation:
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move.
So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.


Note:

The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.

 */
package bfsdfs.hard;

import bfsdfs.UnionFind;

/**
 * Created by poorvank.b on 30/10/18.
 */
public class BrickFallingWhenHit {

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int size = hits.length;
        int[] res = new int[size];
        if(grid == null) {
            return res;
        }
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m*n);

        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    unionAround(i,j,grid,uf,m,n);
                }
            }
        }

        int count = uf.size[uf.find(0)];

        for(int k=size-1;k>=0;k--) {
            int[] hit = hits[k];
            if (grid[hit[0]][hit[1]] == 2) {
                unionAround(hit[0], hit[1], grid, uf,m,n);
                grid[hit[0]][hit[1]] = 1;
            }
            int newCount = uf.size[uf.find(0)];
            int x = (newCount - count > 0) ? newCount - count - 1 : 0;
            res[k]=x;
            count = newCount;
        }

        return res;

    }

    private void unionAround(int x,int y,int[][] grid,UnionFind uf,int m,int n) {

        int[] xDir =  new int[] {-1, 1, 0, 0};
        int[] yDir = new int[] {0, 0, -1, 1};

        for(int k=0;k<4;k++) {

            int nextX = x+xDir[k];
            int nextY = y+yDir[k];
            if(nextX>=0 && nextX<m && nextY>=0 && nextY<n && grid[nextX][nextY]==1) {
                uf.union(x*n+y,nextX*n+nextY);
            }

        }

        // use 0 as pivot to connect all elements in row 0
        if (x == 0) {
            uf.union(x * n + y, 0);
        }

    }

}

/*

If no bircks drop, then after all operations. The grid will be look like a pool with multi islands.
for example:
0010000100
0111001110
1111111111
after operations: [0,2], [3,4], [1,2], [0,7]
0000000000
0101001110
1111011111
so total 2 islands.

Then add bricks back reversely.
[0,7]
0000000100
0101001110
1111011111
the right island attaches top, and its size is 9, which means 8 bricks drop in this operation.

[1,2]
0000000100
0111001110
1111011111
the left island does not reach the top, so no brick drops.

[3,4]
0000000100
0111001110
1111111111
the left island connects to right island and acttaches top, and left island is original 7, which means 7 bricks drop in this operation.

[0,2]
0010000100
0111001110
1111111111
the island size is just enlarged by 1, which means no brick drops.

 */