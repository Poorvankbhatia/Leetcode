/*

Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it
if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

Binary matrix is a matrix with all cells equal to 0 or 1 only.

Zero matrix is a matrix with all cells equal to 0.


Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We don't need to change it.
Example 3:

Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
Output: 6
Example 4:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix can't be a zero matrix


Constraints:

m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1.


 */
package bfsdfs.hard;

import java.util.*;

public class MinimumFlipsZeroMatrix {
    private int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    Set<String> set;
    public int minFlips(int[][] mat) {
        int m = mat.length;
        if(m==0) {
            return 0;
        }
        int n = mat[0].length;
        StringBuilder result = new StringBuilder();
        for (int i=0;i<m*n;i++) {
            result.append("0");
        }
        set = new HashSet<>();
        String hash = hash(mat);
        Queue<String> queue = new LinkedList<>();
        queue.add(hash);
        set.add(hash);
        int ans=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                String poll = queue.poll();
                if(poll.equals(result.toString())) {
                    return ans;
                }
                for (int i=0;i<poll.length();i++) {
                    int x = i/n;
                    int y = i%n;
                    Set<Integer> change = new HashSet<>(); // Set indicating which indexes to change.
                    change.add(i);
                    for (int k=0;k<4;k++) {
                        int nextX = x+dir[k][0];
                        int nextY = y+dir[k][1];
                        if(nextX>=0 && nextY>=0 && nextX<m && nextY<n) {
                            change.add(nextX*n+nextY);
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int j=0;j<poll.length();j++) {
                        sb.append(!change.contains(j)?poll.charAt(j):(poll.charAt(j)=='1'?'0':'1'));
                    }
                    if(set.add(sb.toString())) {
                        queue.add(sb.toString());
                    }
                }
            }
            ans++;
        }
        return -1;
    }


    private String hash(int[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                sb.append(ints[j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,1,1},
                {1,0,1},
                {0,0,0}
        };
        System.out.println(new MinimumFlipsZeroMatrix().minFlips(a));
    }

}
