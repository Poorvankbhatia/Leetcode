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

    private int[] xMove = new int[]{0,1,-1,0};
    private int[] yMOve = new int[]{1,0,0,-1};

    public void solve(char[][] board) {

        int m = board.length;
        if(m==0) {
            return;
        }

        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int j=0;j<n;j++) {

            if(board[0][j]=='O') {
                dfs(0,j,board,visited);
            }
            if(board[m-1][j]=='O') {
                dfs(m-1,j,board,visited);
            }

        }

        for (int i=0;i<m;i++) {

            if(board[i][0]=='O') {
                dfs(i,0,board,visited);
            }
            if(board[i][n-1]=='O') {
                dfs(i,n-1,board,visited);
            }

        }

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(board[i][j] =='B') {
                    board[i][j] = 'O';
                } else if(board[i][j]=='O') {
                    board[i][j] ='X';
                }
            }
        }


    }

    private void dfs(int i,int j,char[][] board,boolean[][] visited) {


        int m = board.length;
        int n = board[0].length;

        visited[i][j] = true;

        if(board[i][j]=='O') {
            board[i][j] = 'B';
        }

        for (int k=0;k<4;k++) {
            int nextX = i + xMove[k];
            int nextY = j + yMOve[k];
            if(nextX>=0 && nextX<m && nextY>=0 && nextY<n && board[nextX][nextY]=='O' && !visited[nextX][nextY]) {
                dfs(nextX,nextY,board,visited);
            }
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