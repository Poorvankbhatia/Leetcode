//return the total number of distinct solutions.
package backtracking.hard;

/**
 * Created by poorvank on 26/08/16.
 */
public class NQueensCount {

    public int totalQueens(int n) {

        char[][] board = new char[n][n];
        return totalQueensUtil(n,board,0);
    }

    private int totalQueensUtil(int n,char[][] board,int col) {

        if(col>=n) {
            return 1;
        }

        int distinctCount = 0;

        for (int row=0;row<n;row++) {

            if(isSafe(board,n,row,col)) {
                board[row][col] = 'Q';
                distinctCount +=totalQueensUtil(n,board,col+1);
                board[row][col] = '.';
            }

        }

        return distinctCount;

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

        NQueensCount nQueens = new NQueensCount();
        System.out.println(nQueens.totalQueens(5));

    }

}
