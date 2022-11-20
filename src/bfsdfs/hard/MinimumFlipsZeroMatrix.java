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
        result.append("0".repeat(Math.max(0, m * n)));
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
/*
O(m * n * 2 ^ (m * n))

class Solution {



    public static void flip(int[][] mat, int n, int m, int i, int j){
        mat[i][j] = mat[i][j] ^ 1;
        if(i - 1 >= 0) mat[i - 1][j] = mat[i - 1][j] ^ 1;
        if(j - 1 >= 0) mat[i][j - 1] = mat[i][j - 1] ^ 1;
        if(i + 1 < n) mat[i + 1][j] = mat[i + 1][j] ^ 1;
        if(j + 1 < m) mat[i][j + 1] = mat[i][j + 1] ^ 1;
    }

    public static int func(int[][] mat, int n, int m, HashSet<String> set, HashMap<String, Integer> dp,String goal){
        StringBuilder t = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                t.append(mat[i][j]+"");
            }
        }

        if(t.toString().equals(goal)) return 0;

        if(dp.containsKey(t.toString())) return dp.get(t.toString());
        if(set.contains(t.toString())) return Integer.MAX_VALUE;

        set.add(t.toString());
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                flip(mat, n, m, i, j);
                int small = func(mat, n, m, set, dp,goal);
                if(small != Integer.MAX_VALUE) min = Math.min(min, 1 + small);
                flip(mat, n, m, i, j);
            }
        }
        set.remove(t.toString());
        dp.put(t.toString(), min);
        return min;
    }

    public int minFlips(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        HashMap<String, Integer> dp = new HashMap<>();
        StringBuilder result = new StringBuilder();
        result.append("0".repeat(Math.max(0, m * n)));
        int ans = func(mat, n, m, new HashSet<>(), dp,result.toString());
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

BIT:


private static final int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}};
    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] visited = new int[1<<(m*n)];
        Queue<Integer> queue = new ArrayDeque<>();
        int start = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                start |= (mat[i][j] << i * n + j);
            }
        }
        queue.offer(start);
        visited[start] = 1;
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int state = queue.poll();
                if (state == 0) {
                    return step;
                }
                for (int x = 0; x < m; x++) {
                    for (int y = 0; y < n; y++) {
                        int ns = flip(state, x, y, m, n);
                        if (visited[ns] == 0) {
                            queue.offer(ns);
                            visited[ns] = 1;
                        }
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private int flip (int state, int x, int y, int m, int n) {
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            state ^= (1 << (nx * n + ny));
        }

        return state;
    }

    Q1: Could you please further explain about the logic of transferring the matrix to int?
sum(cell << (i * n + j) for i, row in enumerate(mat) for j, cell in enumerate(row))
I wonder how it works and why it works.

A1: For Input: mat = [[0,0],[0,1]], map it to 0b1000, that is, mapping mat[i][j] to the (i * n + j)th bit of an int. specifically,
mat[0][0] = 0, corresponds to 0th bit, which is 0;
mat[0][1] = 0, corresponds to 1st bit, which is 0;
mat[1][0] = 0, corresponds to 2nd bit, which is 0;
mat[1][1] = 1, corresponds to 3rd bit, which is 1;

After mapping, any operations on the binary cells of the mat are equals to the operations on the corresponding bits of the mapped int. That's why it works.

Q2: Why do you use "|" to initialize the matrix and use "^" to calculate the next?

A2:

When using 0 |= b, where b = 0 or 1, the result is b; you can change it to
start  += mat[i][j] << (i * n + j);
Use next ^ 1 << k (where k = i * n + j) to flip kth bit of next, which is equal to flipping the corresponding cell (i, j) in the matrix.

 */