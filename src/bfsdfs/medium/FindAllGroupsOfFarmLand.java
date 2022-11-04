/*

You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1
represents a hectare of farmland.

To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not
four-directionally adjacent to another farmland in a different group.

land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right
corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland.
A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].

Return a 2D array containing the 4-length arrays described above for each group of farmland in land.
If there are no groups of farmland, return an empty array. You may return the answer in any order.

Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].

Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].

Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.

 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

public class FindAllGroupsOfFarmLand {

    public int[][] findFarmland(int[][] land) {

        int m = land.length;
        int n = land[0].length;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(land[i][j] == 1 && isStarting(i,j,land)) {
                    list.add(addIndexes(i,j,land));
                }
            }
        }

        return list.toArray(new int[0][]);

    }

    private int[] addIndexes(int i,int j,int[][] land) {
        int[] ans = new int[4];
        ans[0]=i;
        ans[1]=j;

        while (i<land.length-1 && land[i+1][j]==1) i++;
        while (j<land[0].length-1 && land[i][j+1]==1) j++;

        ans[2]=i;
        ans[3]=j;
        return ans;
    }


    private boolean isStarting(int i, int j, int[][] land) {
        if(i>0 && land[i-1][j] == 1) {
            return false;
        }
        if(j>0 && land[i][j-1] == 1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1,0,0},{0,1,1},{0,1,1}
        };
        System.out.println(new FindAllGroupsOfFarmLand().findFarmland(a));
    }

}
