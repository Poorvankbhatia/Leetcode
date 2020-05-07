/*

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its
upper left corner (row1, col1) and lower right corner (row2, col2).

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2

 */
package arrays.hard;

/**
 * Created by poorvank.b on 09/06/18.
 */
public class NumMatrix {

    private int[][] rowSumMatrix;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        rowSumMatrix = matrix;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                rowSumMatrix[i][j] = matrix[i][j] + rowSumMatrix[i][j - 1];
            }
        }

    }

    //O(n)
    public void update(int row, int col, int val) {

        int originalValue = (col == 0) ? rowSumMatrix[row][0] : rowSumMatrix[row][col] - rowSumMatrix[row][col - 1];
        int diff = val - originalValue;
        for (int j = col; j < rowSumMatrix[0].length ; j++) {
            rowSumMatrix[row][j] += diff;
        }

    }

    // O(m)
    public int sumRegion(int row1, int col1, int row2, int col2) {

        int result = 0;

        for (int i = row1; i <= row2; i++) {
            result += (col1 == 0) ? rowSumMatrix[i][col2] : rowSumMatrix[i][col2] - rowSumMatrix[i][col1 - 1];
        }

        return result;
    }

}

/*


If we are going to calculate the whole sum every time the sumRegion function get called,
it will introduce a lot of redundant calculation. Think of 3 situations:

There are a lot of sumRegion function call but very little update function call.
So you want to move all the heavy calculations into update function call. To do this,
you have to maintain a diagnose sum matrix and the sumRegion will take O(1) time because you just
need to return the value in your buffer which you calculated in update function

There are a lot of update but only a few sumRegion function call.
Just update the original matrix and calculate everytime, update function take O(1) time

As this problem indicates, the update and sumRegion function calls are distributed evenly.
What you want to do is to distribute some of the work to update function and some of other work to sumRegion
function so that both of the function will take less than O(mn) time
You can do this by maintaining a row sum matrix. When a cell gets updated, only update the row sum in the same row
which takes O(n) time where n is the length of the matrix
When sumRegion function gets called just add all the row sum with a small trick and it takes O(m) time where m is the height of the matrix

BIT:

https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/

public class NumMatrix {

    int[][] tree;
    int[][] nums;
    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m+1][n+1];
        nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
    }

    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}
// time should be O(log(m) * log(n))


https://www.youtube.com/watch?v=CWDQJGaN1gY


 */