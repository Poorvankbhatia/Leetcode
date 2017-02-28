/*

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8')
represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this
position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares
should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent
mines.
Return the board when no more squares will be revealed.
Example 1:
Input:

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]


 Input:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 */
package bfsdfs.medium;

/**
 * Created by poorvank on 26/02/17.
 */
public class MineSweeper {

    int[] xMove = new int[]{0,0,-1,1,-1,1,1,-1};
    int[] yMove = new int[]{1,-1,0,0,1,1,-1,-1};

    public char[][] updateBoard(char[][] board, int[] click) {

        int m = board.length;
        int n = board[0].length;

        int x = click[0];
        int y = click[1];

        if(board[x][y]=='M') {
            board[x][y] = 'X';
        } else {
            int count = 0;
            for (int k=0;k<8;k++) {
                int nextX = x+xMove[k];
                int nextY = y+yMove[k];
                if(isValid(nextX,nextY,m,n)) {
                    if(board[nextX][nextY]=='M') {
                        count++;
                    }
                }
            }

            if(count>0) {
                board[x][y] = (char)(count + '0');
            } else {
                board[x][y] = 'B';
                for (int k=0;k<8;k++) {
                    int nextX = x+xMove[k];
                    int nextY = y+yMove[k];
                    if(isValid(nextX,nextY,m,n)) {
                        if(board[nextX][nextY]=='E') {
                            updateBoard(board,new int[]{nextX,nextY});
                        }
                    }
                }
            }
        }
        return board;

    }


    private boolean isValid(int x,int y,int rows,int cols) {
        return (x<rows && y<cols && x>=0 && y>=0);
    }

    public static void main(String[] args) {

        char[][] grid = new char[][] {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };

        grid = new MineSweeper().updateBoard(grid,new int[]{3,0});

        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

}

/*

If click on a mine ('M'), mark it as 'X', stop further search.
If click on an empty cell ('E'), depends on how many surrounding mine:
2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.

 */
