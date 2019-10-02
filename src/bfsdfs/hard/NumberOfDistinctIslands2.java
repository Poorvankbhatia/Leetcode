/*

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation
(90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1.

Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:
111
1
and
1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
Note: The length of each dimension in the given grid does not exceed 50.

 */
package bfsdfs.hard;

import java.util.*;

public class NumberOfDistinctIslands2 {

    int[][] dirs={{-1,0}, {1,0}, {0,-1}, {0,1}};
    int[][] trans={{1,1}, {1,-1}, {-1,1}, {-1,-1}};

    public int numDistinctIslands2(int[][] grid) {
        if (grid==null || grid.length==0 || grid[0].length==0) return 0;
        int m=grid.length, n=grid[0].length;
        Set<String> islands=new HashSet<>();

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==1){
                    List<int[]> cells=new ArrayList<>();
                    dfs(grid, i, j, cells);
                    String key=norm(cells);
                    islands.add(key);
                }
            }
        }
        return islands.size();
    }

    private void dfs(int[][] grid, int i, int j, List<int[]> cells){
        cells.add(new int[]{i, j});
        grid[i][j]=-1;

        for (int[] dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if (x>=0 && x<grid.length && y>=0 && y<grid[0].length && grid[x][y]==1)
                dfs(grid, x, y, cells);
        }
    }

    private String norm(List<int[]>cells){
        List<String> forms=new ArrayList<>();
        // generate the 8 different transformations
        // (x, y), (x, -y), (-x, y), (-x, -y)
        // (y, x), (-y, x), (y, -x), (-y, -x)
        for (int[] ts:trans){
            List<int[]> list1=new ArrayList<>();
            List<int[]> list2=new ArrayList<>();
            for (int[] cell:cells){
                list1.add(new int[]{cell[0]*ts[0], cell[1]*ts[1]});
                list2.add(new int[]{cell[1]*ts[1], cell[0]*ts[0]});
            }
            forms.add(getKey(list1));
            forms.add(getKey(list2));
        }

        // sort the keys: take the first one as the representative key
        Collections.sort(forms);
        return forms.get(0);
    }

    private String getKey(List<int[]>cells){
        // sort the cells before generate the key
        Collections.sort(cells, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0]!=b[0]){
                    return a[0]-b[0];
                }else{
                    return a[1]-b[1];
                }
            }
        });

        StringBuilder sb=new StringBuilder();
        int x=cells.get(0)[0], y=cells.get(0)[1];
        for (int[] cell:cells)
            sb.append(cell[0] - x).append(":").append(cell[1] - y).append(":");

        return sb.toString();
    }

}

/*

After we get coordinates for an island, compute all possible rotations/reflections (https://en.wikipedia.org/wiki/Dihedral_group)
of it and then sort them using the default comparison. The first representation in this order is then the canonical one.

 */