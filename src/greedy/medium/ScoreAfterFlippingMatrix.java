/*

We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the
sum of these numbers.

Return the highest possible score.



Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39


Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.

 */
package greedy.medium;

/**
 * Created by poorvank.b on 21/07/18.
 */
public class ScoreAfterFlippingMatrix {

    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            if (A[i][0] == 0) {
                flipRow(A, i);
            }
        }
        for (int j = 0; j < n; j++) {
            if (moreZero(A, j)) {
                flipCol(A, j);
            }
        }
        return calc(A);
    }
    private void flipRow(int[][] A, int i) {
        for (int j = 0; j < A[0].length; j++) {
            A[i][j] = 1 - A[i][j];
        }
    }
    private boolean moreZero(int[][] A, int j) {
        int count = 0;
        for (int[] aA : A) {
            if (aA[j] == 0) {
                count++;
            }
        }
        return count > A.length / 2;
    }
    private void flipCol(int[][] A, int j) {
        for (int i = 0; i < A.length; i++) {
            A[i][j] = 1 - A[i][j];
        }
    }
    private int calc(int[][] A) {
        int result = 0;
        for (int[] aA : A) {
            int temp = 0;
            for (int j = 0; j < A[0].length; j++) {
                if (aA[j] == 1) {
                    temp += 1 << (A[0].length - j - 1);
                }
            }
            result += temp;
        }
        return result;
    }

}

/*

In binary representation, the following holds true:

100 > 011
1000 > 0111
10000 > 01111
etc.
That is, the first bit gives us more upside than all the rest bits combined. Thus, first and foremost,
we flip all rows that have the largest bit equal to zero (step 1).
Afterwards, we try to maximize what's left. We no longer want to flip rows, as this would turn our largest
and most valuable bits to zero. Instead, we go over each column and see if it makes sense to flip it.
Whenever the number of zeros in a column is more than the number of ones, it makes sense to flip it. That's what we do in step 2.

Basically, it's just a greedy approach.

 */