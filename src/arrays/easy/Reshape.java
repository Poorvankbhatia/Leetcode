/*

In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but
keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column
number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
Input:
nums =
[[1,2],
 [3,4]]
r = 1, c = 4
Output:
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is an 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input:
nums =
[[1,2],
 [3,4]]
r = 2, c = 4
Output:
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.

 */
package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank.b on 30/04/17.
 */
public class Reshape {

    public static int[][] matrixReshape(int[][] A, int m, int n) {
        int origM = A.length;
        int origN = A[0].length;
        if(origM*origN != m*n){
            return A;
        }
        int[][] B = new int[m][n];
        int[] A1D = new int[A.length * A[0].length];

        int index = 0;
        for(int i = 0;i<origM;i++){
            for(int j = 0;j<origN;j++){
                A1D[index++] = A[i][j];
            }
        }

        System.out.println(Arrays.toString(A1D));

        index = 0;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                B[i][j] = A1D[index++];
            }

        }
        return B;
    }

    public static void main(String[] args) {
        int[][] ori = new int[][] { {1,2,3}, {4,5,6}, {7,8,9}, {10,11,12} };
        int[][] res = matrixReshape(ori,2,6);

        for(int i = 0;i<ori.length;i++){
            for(int j = 0;j<ori[0].length;j++){
                System.out.print(ori[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i = 0;i<res.length;i++){
            for(int j = 0;j<res[0].length;j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.println("");
        }
    }

}
