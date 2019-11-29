/*

You are given a map of a server center, represented as a m * n integer matrix grid, where 1
means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.

Return the number of servers that communicate with any other server.


Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.


Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.

Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other.
 The server at right bottom corner can't communicate with any other server.


Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1

 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

public class CountServers {

    int[] size;
    int[] parent;

    public int countServers(int[][] grid) {

        int m = grid.length;
        if(m==0) {
            return 0;
        }
        int n = grid[0].length;
        size = new int[m*n];
        parent = new int[m*n];

        List<int[]> list = new ArrayList<>();
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    list.add(new int[]{i,j});
                    parent[(i*n+j)]=(i*n+j);
                    size[(i*n+j)]=1;
                }
            }
        }

        for (int i=0;i<list.size();i++) {
            for (int[] ints : list) {
                if (list.get(i)[0] == ints[0] || list.get(i)[1] == ints[1]) {
                    int a = list.get(i)[0] * n + list.get(i)[1];
                    int b = ints[0] * n + ints[1];
                    if (!isConnected(a, b)) {
                        connect(a, b);
                    }
                }
            }
        }

        int count=0;
        for (int s : size) {
            if(s>1) {
                count+=s;
            }
        }

        return count;
    }

    private boolean isConnected(int a,int b) {
        return getParent(a)==getParent(b);
    }

    private int getParent(int x) {
        while (x!=parent[x]) {
            parent[x] = parent[parent[x]];
            x=parent[x];
        }
        return x;
    }

    private void connect(int a,int b) {
        int pA = getParent(a);
        int pB = getParent(b);
        if(pA!=pB) {
            if(size[pA]>size[pB]) {
                parent[pB]=pA;
                size[pA]+=size[pB];
                size[pB]=0;
            } else {
                parent[pA]=pB;
                size[pB]+=size[pA];
                size[pA]=0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,0,1,0,1},
                {0,1,0,1,0},
                {0,1,1,1,0},
                {1,0,0,1,1},
                {0,0,1,1,0}
        };
        System.out.println(new CountServers().countServers(a));
    }

}

/*

Better solution without UF:

public int countServers(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int numRows = grid.length;
        int numCols = grid[0].length;
        int rowCount[] = new int[numRows];
        int colCount[] = new int[numCols];
        int totalServers = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == 1) {
                    rowCount[row]++;
                    colCount[col]++;
                    totalServers++;
                }
            }
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == 1) {
                    if (rowCount[row] == 1 && colCount[col] == 1) {
                        totalServers--;
                    }
                }
            }
        }
        return totalServers;
    }

 */