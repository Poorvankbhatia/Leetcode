/*

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


Example 1:

Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: True
Explanation:
1234
5123
9512

In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]",
and in each diagonal all elements are the same, so the answer is True.
Example 2:

Input: matrix = [[1,2],[2,2]]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.
Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].

 */
package arrays.easy;

/**
 * Created by poorvank.b on 21/01/18.
 */
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {

        int m = matrix.length;


        int n = matrix[0].length;

        for (int j=0;j<n;j++) {

            if(!haveSameElementsAcross(matrix,0,j,m,n)) {
                return false;
            }

        }

        for (int i=0;i<m;i++) {

            if(!haveSameElementsAcross(matrix,i,0,m,n)) {
                return false;
            }

        }

        return true;

    }

    private boolean haveSameElementsAcross(int[][] matrix,int i,int j,int m,int n) {

        int res = matrix[i][j];

        while (++i<m && ++j<n) {
            if(res!=matrix[i][j]) {
                return false;
            }
        }

        return true;

    }

}
