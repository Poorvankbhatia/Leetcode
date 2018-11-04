/*

There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or
cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of
paths to move the ball out of grid boundary. The answer may be very large, return it after mod 10^9 + 7.


Example 1:
Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6

Example 2:
Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].

 */
package dyanamicprogramming.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 08/05/17.
 */
public class OutOfBoundary {

    private Map<String,Integer> map;
    public int findPaths(int m, int n, int N, int i, int j) {
        map = new HashMap<>();
        return count(m,n,N,i,j);
    }

    private int count(int m,int n,int step,int i,int j) {

        if (i < 0 || j < 0 || i == m || j == n) {
            return 1;
        }
        if (step == 0) {
            return 0;
        }

        String key = i + "_" + j + "_" + step;

        Integer result = map.get(key);
        long mod = 1000000007L;
        if (map.get(key) != null) {
            return result;
        }


        long path = (count(m, n, step - 1, i + 1, j) % mod + count(m, n, step - 1, i, j + 1) % mod +
                     count(m, n, step - 1, i - 1, j) % mod + count(m, n, step - 1, i, j - 1) % mod) % mod;

        map.put(key, (int) path);
        return (int) path;

    }

}
