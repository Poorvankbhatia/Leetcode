/*

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.

 */
package backtracking.hard;

/**
 * Created by poorvank on 8/10/16.
 */
public class SudokuSolver {

    private static final int N = 9;
    private static int row,col;

    public void solveSudoku(char[][] board) {
        if(solveSudokuUtil(board)) {
            print(board);
        }
    }

    public void print(char[][] board) {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                System.out.print(board[i][j] +" ");
            }
            System.out.println();
        }
    }

    private boolean solveSudokuUtil(char[][] board) {
        if (unAssigned(board)) {
            return true;
        }
        /*

        We are not looping over all the rows and colums.
        like:   for (int i=0;i<N;i++) {
                    for (int j=0;j<N;j++) {}}

        rather every time we use the unAssigned function to calculate the cell with '.'

         */
        for (char c = '1'; c <= '9'; c++) {
            if (isSafe(c, board)) {
                board[row][col] = c;
                int currentR = row;
                int currentC = col;
                //System.out.println(row + " " + col +" " + c);
                if (solveSudokuUtil(board)) {
                    return true;
                }
                row = currentR;
                col = currentC;
                //System.out.println(row + " " + col +" " + '.');
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isSafe(char c,char[][] board) {
        return !usedInRow(c,board) && !usedInCol(c,board) && !usedInBox(c,row-row%3,col-col%3,board);
    }

    private boolean usedInRow(char c,char[][] board) {
        for (int j=0;j<N;j++) {
            if(board[row][j]==c) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(char c,char[][] board) {
        for (int i=0;i<N;i++) {
            if(board[i][col]==c) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(char c,int boxStartR,int boxStartC,char[][] board) {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if(board[i+boxStartR][j+boxStartC]==c) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean unAssigned(char[][] board) {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if(board[i][j]=='.') {
                    row = i;
                    col = j;
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'3', '.', '6', '5', '.', '8', '4', '.', '.'},
                                     {'5', '2', '.', '.', '.', '.', '.', '.', '.'},
                                     {'.', '8', '7', '.', '.', '.', '.', '3', '1'},
                                     {'.', '.', '3', '.', '1', '.', '.', '8', '.'},
                                     {'9', '.', '.', '8', '6', '3', '.', '.', '5'},
                                     {'.', '5', '.', '.', '9', '.', '6', '.', '.'},
                                     {'1', '3', '.', '.', '.', '.', '2', '5', '.'},
                                     {'.', '.', '.', '.', '.', '.', '.', '7', '4'},
                                     {'.', '.', '5', '2', '.', '6', '3', '.', '.'}};
        new SudokuSolver().solveSudoku(grid);
    }


}


/*

Sudoku by one by one assigning numbers to empty cells. Before assigning a number,
we check whether it is safe to assign. We basically check that the same number is not present in current row,
current column and current 3X3 subgrid. After checking for safety, we assign the number, and recursively check whether
this assignment leads to a solution or not. If the assignment doesn’t lead to a solution, then we try next number for current empty cell.
And if none of number (1 to 9) lead to solution, we return false.

  Find row, col of an unassigned cell
  If there is none, return true
  For digits from 1 to 9
    a) If there is no conflict for digit at row,col
        assign digit to row,col and recursively try fill in rest of grid
    b) If recursion successful, return true
    c) Else, remove digit and try another
  If all digits have been tried and nothing worked, return false

 */