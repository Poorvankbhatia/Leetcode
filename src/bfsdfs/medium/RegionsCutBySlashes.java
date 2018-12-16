/*

In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.
These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

Input:
[
  " /",
  "/ "
]
Output: 2

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:





 */
package bfsdfs.medium;

/**
 * Created by poorvank.b on 16/12/18.
 */
public class RegionsCutBySlashes {

    public int regionsBySlashes(String[] grid) {
        int n = grid.length, m = grid[0].length();
        int cnt = 0;
        int[][] g = new int[n*3][m*3];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='/'){
                    g[i * 3][j * 3 + 2] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3] = 1;
                }else if(grid[i].charAt(j)=='\\'){
                    g[i * 3][j * 3] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        }
        for(int i=0;i<g.length;i++){
            for(int j=0;j<g[0].length;j++){
                if(g[i][j]==0){
                    dfs(g,i,j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void dfs(int[][] g, int i, int j){
        int n = g.length, m = g[0].length;
        if(i<0 || i>=n || j<0 || j>=m || g[i][j]==1) return;
        g[i][j]=1;
        int d[] = {0,-1,0,1,0};
        for(int k=0;k<4;k++){
            dfs(g,i+d[k],j+d[k+1]);
        }
    }

}

/*

One "brute force" way to specify the graph is to associate each grid square with 4 nodes
(north, south, west, and east), representing 4 triangles inside the square if it were to have both slashes.
Then, we can connect all 4 nodes if the grid square is " ", and connect two pairs if the grid square is "/" or "\".
Finally, we can connect all neighboring nodes (for example, the east node of the square at grid[0][0] connects
with the west node of the square at grid[0][1]).

 */