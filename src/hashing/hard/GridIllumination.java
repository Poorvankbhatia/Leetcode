/*

On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.

Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp that
is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).

For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.

After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are adjacent
8-directionally (ie., share a corner or edge with cell (x, y).)

Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].



Example 1:

Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
Output: [1,0]
Explanation:
Before performing the first query we have both lamps [0,0] and [4,4] on.
The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the bottom right corner:
1 1 1 1 1
1 1 0 0 1
1 0 1 0 1
1 0 0 1 1
1 1 1 1 1
Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and the grid now looks like this:
1 0 0 0 1
0 1 0 0 1
0 0 1 0 1
0 0 0 1 1
1 1 1 1 1
Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the cell is no longer lit.


Note:

1 <= N <= 10^9
0 <= lamps.length <= 20000
0 <= queries.length <= 20000
lamps[i].length == queries[i].length == 2

 */
package hashing.hard;

import java.util.HashMap;
import java.util.Map;

public class GridIllumination {
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}, {0,0}};

    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> m1 = new HashMap<>();       // row number to count of lamps
        Map<Integer, Integer> m2 = new HashMap<>();       // col number to count of lamps
        Map<Integer, Integer> m3 = new HashMap<>();       // diagonal x-y to count of lamps
        Map<Integer, Integer> m4 = new HashMap<>();       // diagonal x+y to count of lamps
        Map<Integer, Boolean> m5 = new HashMap<>();       // whether lamp is  ON|OFF

        // increment counters while adding lamps
        for (int[] lamp : lamps) {
            int x = lamp[0];
            int y = lamp[1];
            m1.put(x, m1.getOrDefault(x, 0) + 1);
            m2.put(y, m2.getOrDefault(y, 0) + 1);
            m3.put(x - y, m3.getOrDefault(x - y, 0) + 1);
            m4.put(x + y, m4.getOrDefault(x + y, 0) + 1);
            m5.put(N * x + y, true);
        }

        int[] ans = new int[queries.length];
        // address queries
        for(int i=0; i<queries.length; i++){
            int x = queries[i][0];
            int y = queries[i][1];

            ans[i] = (m1.getOrDefault(x, 0) > 0 || m2.getOrDefault(y, 0) > 0 || m3.getOrDefault(x-y, 0) > 0 || m4.getOrDefault(x+y, 0) > 0) ? 1 : 0;
            // switch off the lamps, if any
            for (int[] dir : dirs) {
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                if (m5.containsKey(N * x1 + y1) && m5.get(N * x1 + y1)) {
                    // the lamp is on, turn it off, so decrement the count of the lamps
                    m1.put(x1, m1.getOrDefault(x1, 1) - 1);
                    m2.put(y1, m2.getOrDefault(y1, 1) - 1);
                    m3.put(x1 - y1, m3.getOrDefault(x1 - y1, 1) - 1);
                    m4.put(x1 + y1, m4.getOrDefault(x1 + y1, 1) - 1);
                    m5.put(N * x1 + y1, false);
                }
            }
        }

        return ans;
    }
}

/*

The basic idea is:

The row, column or diagonal will remain illuminated if there are > 0 lamps on that particular row, column or diagonal
all the diagonals with slope= 1, can be represented by x= y+c i.e. they have x-y = constant
all the diagonals with slope= -1, can be represented by x= -y+c i.e they have x+y = constant
store the counts in separate maps
When a lamp is turned off, the count of lamps in respective row, column or diagonal decreases by 1

 */