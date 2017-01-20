/*

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

 */
package arrays.medium;

/**
 * Created by poorvank on 20/01/17.
 */
public class SetMatrixZeros {

    public void setZeroes(int[][] matrix) {

        if(matrix.length==0) {
            return;
        }

        //Check if first row and column have to be replaced with 0's
        boolean firstRowZero = false,firstColZero = false;

        for (int i=0;i<matrix.length;i++) {
            if(matrix[i][0]==0) {
                firstColZero = true;
            }
        }

        for (int j=0;j<matrix[0].length;j++) {
            if(matrix[0][j]==0) {
                firstRowZero = true;
            }
        }


        for (int i=1;i<matrix.length;i++) {
            for (int j=1;j<matrix[0].length;j++) {

                if(matrix[i][j]==0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }

            }
        }

        for (int i=1;i<matrix.length;i++) {
            for (int j=1;j<matrix[0].length;j++) {
                if(matrix[i][0]==0 || matrix[0][j]==0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(firstRowZero) {
            for (int i=0;i<matrix[0].length;i++) {
                matrix[0][i] = 0;
            }
        }


        if(firstColZero) {
            for (int i=0;i<matrix.length;i++) {
                matrix[i][0] = 0;
            }
        }


    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,0,3}};
        new SetMatrixZeros().setZeroes(matrix);
    }

}
