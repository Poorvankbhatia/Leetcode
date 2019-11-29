/*

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

 */

package bfsdfs.medium;
/**
 * Created by poorvank on 29/03/17.
 */
public class SurroundedRegions {

    int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public void solve(char[][] board) {
        if(board==null || board.length==0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j]=='O' && (i==0 || j==0 || i==m-1 || j==n-1)) {
                    dfs(board,i,j,visited);
                }
            }
        }

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(board[i][j]=='B') {
                    board[i][j]='O';
                } else {
                    board[i][j]='X';
                }
            }
        }

    }

    private void dfs(char[][] grid,int i,int j,boolean[][] visited) {
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[i][j] || grid[i][j]=='X') {
            return;
        }
        if(grid[i][j]=='O') {
            grid[i][j]='B';
        }
        visited[i][j]=true;
        for(int k=0;k<4;k++) {
            int nextX = i+dir[k][0];
            int nextY = j+dir[k][1];
            dfs(grid,nextX,nextY,visited);
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X','O','O','X'},
                {'X','O','X','X'},
                {'X','X','O','X'},
                {'X','X','X','X'}
        };
        new SurroundedRegions().solve(board);
    }


}


/*

Just find our all the boundary O's and make them B's.. Do a dfs so that all attached O's become B's.

Then loop once more and convert

Another way.

Union Find data structure:
The idea comes from the observation that if a region is NOT captured,
it is connected to the boundry. So if we connect all the 'O' nodes on the boundry to a dummy node, and then connect each 'O'
node to its neighbour 'O' nodes, then we can tell directly whether a 'O' node is captured by checking whether it is connected to the dummy node.

https://discuss.leetcode.com/topic/1944/solve-it-using-union-find/2


 */