/*

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 28/11/16.
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;

        if (rows == 0) {
            return 0;
        }

        int cols = matrix[0].length;

        if (cols == 1 && rows == 1) {
           return matrix[0][0];
        } else if (cols == 1) {
            for (char[] aMatrix : matrix) {
                if (aMatrix[0] == '1') {
                    return 1;
                }
            }
            return 0;
        } else if (rows == 1) {
            for (int i = 0; i < cols; i++) {
                if (matrix[0][i] == '1') {
                    return 1;
                }
            }
            return 0;
        }


        int[][] s = new int[rows][cols];

        int result = 0;

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(i==0 || j==0) {
                    s[i][j] = matrix[i][j]-'0';
                    result = Math.max(result,s[i][j]);
                } else if(matrix[i][j]=='1') {
                    s[i][j] = Math.min(Math.min((s[i-1][j]),(s[i][j-1])),(s[i-1][j-1]))+1;
                    result = Math.max(s[i][j],result);
                }
            }
        }

        return result*result;

    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(new MaximalSquare().maximalSquare(matrix));
    }

}

/*

Top, Left, and Top Left decides the size of the square. If all of them are same, then the size of square increases by 1.
If they're not same, they can increase by 1 to the minimal square.

 */
