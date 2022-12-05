/*

You are given an m x n integer matrix grid containing distinct positive integers.

You have to replace each integer in the matrix with a positive integer satisfying the following conditions:

The relative order of every two elements that are in the same row or column should stay the same after the replacements.
The maximum number in the matrix after the replacements should be as small as possible.
The relative order stays the same if for all pairs of elements in the original matrix such that grid[r1][c1] >
grid[r2][c2] where either r1 == r2 or c1 == c2, then it must be true that grid[r1][c1] > grid[r2][c2] after the replacements.

For example, if grid = [[2, 4, 5], [7, 3, 9]] then a good replacement could be either grid = [[1, 2, 3], [2, 1, 4]] or
grid = [[1, 2, 3], [3, 1, 4]].

Return the resulting matrix. If there are multiple answers, return any of them.

3 1  => 2 1
2 5     1 2

Input: grid = [[3,1],[2,5]]
Output: [[2,1],[1,2]]
Explanation: The above diagram shows a valid replacement.
The maximum number in the matrix is 2. It can be shown that no smaller value can be obtained.
Example 2:

Input: grid = [[10]]
Output: [[1]]
Explanation: We replace the only number in the matrix with 1.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
1 <= grid[i][j] <= 109
grid consists of distinct integers.

 */
package heap.hard;

import java.util.PriorityQueue;

public class MinimizeMaximizeValueGrid {
    public int[][] minScore(int[][] A) {
        int rows = A.length, cols = A[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> A[a[0]][a[1]] - A[b[0]][b[1]]);

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                pq.offer(new int[]{i, j});
            }
        }

        int[] row_max = new int[rows], col_max = new int[cols];
        int[][] res = new int[rows][cols];

        while(pq.size() > 0){
            int[] curr = pq.poll();

            int curr_row = curr[0], curr_col = curr[1];

            int val = Math.max(row_max[curr_row], col_max[curr_col]) + 1;
            res[curr_row][curr_col] = val;
            row_max[curr_row] = val;
            col_max[curr_col] = val;
        }

        return res;
    }
}

/*

Time complexity: O(m*n* log(mn))

~~ as we traverse all the elements in the matrix, that costs us m*n time,
and for each element, we make an entry in the PQ,
which will cost is log(size of PQ) each time.
Also, max size of PQ = m*n

Here, m = #rows, n = #cols

Space Complexity: O(m*n)

Also see : matrixRankTransform


Observations

The elements of the grid are distinct
We need to make changes such that the max element in the grid is minimised
The relative order needs to be kept intact for each pair in the same row ans col
Idea

The 1st thought is to sort row and col wise and starting from the smallest, put values from 1, 2.... in the row and col
However, that wont be the optimal solution as the result matrix element does not need to be distinct
In stead, we maintain a matrix for rows and cols to store the max values in each row and col
Hence, the new val for cell will be the next possible value, i.e the max (max of that row, max of that col) + 1
A Priority Queue is used to store the indices in order as shown below
Explaining the above logic in words is quite a challenge, hence here is a simulation for the algorithm.
Hope it makes sense.

 */
