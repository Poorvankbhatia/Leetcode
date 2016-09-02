/*

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an
empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

 */
package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 26/08/16.
 */
public class NQueenConfig {

    List<List<String>> lists = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {

        char[][] array =new char[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                array[i][j] ='.';
            }
        }

        nQueenUtil(array,0,n);
        return lists;
    }

    private List<String> boardToList(char[][] board,int n) {

        List<String> list = new ArrayList<>();
        for (int i=0;i<n;i++) {
            char[] array = board[i];
            String str = new String(array);
            list.add(str);
        }
        return list;
    }


    private void nQueenUtil(char[][] board,int col,int n) {

        if(col>=n) {
            List<String> list = boardToList(board,n);
            lists.add(list);
            return;
        }

        for (int row=0;row<n;row++) {

            if(isSafe(board,n,row,col)) {

                board[row][col] = 'Q';
                nQueenUtil(board,col+1,n);
                board[row][col] = '.';

            }

        }


    }

    private boolean isSafe(char[][] board,int n,int row,int col) {

        for (int j=0;j<col;j++) {
            if(board[row][j]=='Q') {
                return false;
            }
        }

        for (int i=row,j=col;i>=0&&j>=0;i--,j--) {
            if(board[i][j]=='Q') {
                return false;
            }
        }

        for (int i=row,j=col;i<n&&j>=0;i++,j--) {
            if(board[i][j]=='Q') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        NQueenConfig nQueens = new NQueenConfig();
        System.out.println(nQueens.solveNQueens(4));

    }

}
