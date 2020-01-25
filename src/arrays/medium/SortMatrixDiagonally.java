/*
Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.

Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
 */
package arrays.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int row = m - 1, c = 0; row >= 0; row--) {
            fillMatrix(mat, m, n, row, c);
        }
        for (int col = 1, r = 0; col < n - 1; col++) {
            fillMatrix(mat, m, n, r, col);
        }
        return mat;
    }
    private void fillMatrix(int[][] mat, int m, int n, int r, int c) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; r + i < m && c + i < n; i++) {
            arr.add(mat[r + i][c + i]);
        }
        Collections.sort(arr);
        for (int i = 0; r + i < m && c + i < n; i++) {
            mat[r + i][c + i] = arr.get(i);
        }
    }

}
