/*

We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.
Each glass holds one cup (250ml) of champagne.

Then, some champagne is poured in the first glass at the top.  When the top most glass is full, any excess liquid poured will fall equally
 to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the
 left and right of those glasses, and so on.  (A glass at the bottom row has it's excess champagne fall on the floor.)

For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two
glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses
total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full,


Now after pouring some non-negative integer cups of champagne, return how full the j-th glass in the i-th row is (both i and j are 0 indexed.)



Example 1:
Input: poured = 1, query_glass = 1, query_row = 1
Output: 0.0
Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)).
There will be no excess liquid so all the glasses under the top glass will remain empty.

Example 2:
Input: poured = 2, query_glass = 1, query_row = 1
Output: 0.5
Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)).
There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share
the excess liquid equally, and each will get half cup of champange.


Note:

poured will be in the range of [0, 10 ^ 9].
query_glass and query_row will be in the range of [0, 99].

 */
package arrays.medium;

/**
 * Created by poorvank.b on 11/03/18.
 */
public class ChampagneTower {

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] A = new double[102][102];
        A[0][0] = (double) poured;
        for (int row = 0; row <= query_row; ++row) {
            for (int col = 0; col <= row; ++col) {
                double q = (A[row][col] - 1.0) / 2.0;
                if (q > 0) {
                    A[row+1][col] += q;
                    A[row+1][col+1] += q;
                }
            }
        }

        return Math.min(1, A[query_row][query_glass]);
    }

    public static void main(String[] args) {
        System.out.println(new ChampagneTower().champagneTower(2,0,0));
    }

}

/*

Instead of keeping track of how much champagne should end up in a glass, keep track of the total amount of champagne that flows
through a glass. For example, if poured = 10 cups are poured at the top, then the total flow-through of the top glass is 10;
the total flow-through of each glass in the second row is 4.5, and so on.

Algorithm

In general, if a glass has flow-through X, then Q = (X - 1.0) / 2.0 quantity of champagne will equally flow left and right.
We can simulate the entire pour for 100 rows of glasses. A glass at (r, c) will have excess champagne flow towards (r+1, c) and (r+1, c+1).


Time Complexity: O(R^2) where R is the number of rows. As this is fixed, we can consider this complexity to be O(1).

Space Complexity: O(R^2) or O(1) by the reasoning above.

 */