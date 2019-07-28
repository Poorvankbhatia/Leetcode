/*

Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border,
or 0 if such a subgrid doesn't exist in the grid.



Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
Example 2:

Input: grid = [[1,1,0,0]]
Output: 1


Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1

 */
package arrays.medium;

public class Largest1borderedSquare {

    public int largest1BorderedSquare(int[][] grid) {
        if (grid.length==0) return 0;
        int[][] horizontal = new int[grid.length+1][grid[0].length+1];
        int[][] vertical = new int[grid.length+1][grid[0].length+1];
        int dist, max=0;
        for (int r=1;r<=grid.length;r++){
            for (int c=1;c<=grid[0].length;c++){
                if (grid[r-1][c-1]==0) continue;
                horizontal[r][c] = horizontal[r-1][c]+1;
                vertical[r][c] = vertical[r][c-1]+1;
                dist = Math.min(horizontal[r][c],vertical[r][c]);
                for (int i=dist;i>=1;i--){
                    if (horizontal[r][c-i+1]>=i
                            && vertical[r-i+1][c]>=i){
                        max = Math.max(max, i*i);
                        break;
                    }
                }
            }
        }
        return max;
    }
}

/*
Create auxillary horizontal and vertical arrays first
Look Images.
Then starting from bottom right,for every i,j ; we find small=min (ver[i][j], hor[i][j]) (marked in orange) ,
then look at all distances in [1,small] vertically in hor array and horizontally in ver array. If values(shown in blue)
are greater than small and if small is greater than curr result, then we update result
 */