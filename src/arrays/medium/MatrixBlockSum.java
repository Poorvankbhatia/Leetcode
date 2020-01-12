/*
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements
mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.


Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
 */
package arrays.medium;

public class MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        int[][] pre = new int[m][n];
        for(int i = 0; i < m; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                sum += mat[i][j];
                pre[i][j] = sum;
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - k);
                int r2 = Math.min(m - 1, i + k);
                int c1 = Math.max(0, j - k);
                int c2 = Math.min(n - 1, j + k);
                for(int t = r1; t <= r2; t++) {
                    ans[i][j] += pre[t][c2];
                    if(c1 != 0) ans[i][j] -= pre[t][c1 - 1];
                }
            }
        }
        return ans;
    }

}
