/*

Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.
Example:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |

 */
package arrays.Hard;

/**
 * Created by poorvank.b on 31/12/17.
 */
public class SparseMatrixMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {

        if (A == null || A.length == 0 ||
                B == null || B.length == 0) {
            return new int[0][0];
        }

        int[][] C = new int[A.length][B[0].length];
        for(int i=0; i<C.length; i++){
            for(int j=0; j<C[0].length; j++){
                int sum=0;
                for(int k=0; k<A[0].length; k++){
                    sum += A[i][k]*B[k][j];
                }
                C[i][j] = sum;
            }
        }

        return C;
    }

}
