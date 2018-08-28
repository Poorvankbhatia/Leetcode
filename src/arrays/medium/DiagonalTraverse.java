/*

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]

 */
package arrays.medium;

/**
 * Created by poorvank on 17/05/17.
 */
public class DiagonalTraverse {

    class Solution {
        public int[] findDiagonalOrder(int[][] matrix) {
            if(matrix==null || matrix.length==0) {
                return new int[0];
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int[] res = new int[m * n];
            boolean up = true;
            int r = 0, c = 0;
            for(int i = 0; i < m * n; i++){
                res[i] = matrix[r][c];
                if(up){
                    r--;
                    c++;
                } else {
                    r++;
                    c--;
                }

                if(r >= m) {
                    r = m - 1;
                    c += 2;
                    up=true;
                }
                else if(c >= n) {
                    c = n - 1;
                    r += 2;
                    up=false;
                }
                else if(r < 0){
                    r = 0;
                    up=false;
                }
                else if(c < 0){
                    c = 0;
                    up=true;
                }
            }
            return res;
        }
    }

}

/*

Walk patterns:

If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
if out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
if out of top border (row < 0) then row = 0; change walk direction.
if out of left border (col < 0) then col = 0; change walk direction.
Otherwise, just go along with the current direction.
Time complexity: O(m * n), m = number of rows, n = number of columns.
Space complexity: O(1).

 */