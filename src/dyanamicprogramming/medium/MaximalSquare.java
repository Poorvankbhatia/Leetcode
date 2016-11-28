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
            if (matrix[0][0] == '0') {
                return 0;
            } else {
                return 1;
            }
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

        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == '0') {
                s[0][i] = 0;
            } else {
                s[0][i] = 1;
                result = 1;
            }
        }

        for (int j = 0; j < rows; j++) {
            if (matrix[j][0] == '0') {
                s[j][0] = 0;
            } else {
                s[j][0] = 1;
                result = 1;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    s[i][j] = Math.min(Math.min(s[i - 1][j], s[i][j - 1]), s[i - 1][j - 1]) + 1;
                    result = Math.max(result,s[i][j]);
                }
            }
        }

        return result*result;



    }

}

/*

Top, Left, and Top Left decides the size of the square. If all of them are same, then the size of square increases by 1.
If they're not same, they can increase by 1 to the minimal square.

 */
