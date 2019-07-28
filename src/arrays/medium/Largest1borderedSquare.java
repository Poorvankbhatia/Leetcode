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
        int m=grid.length,n=grid[0].length;
        int[][] left=new int[m][n];
        int[][] up=new int[m][n];
        int max=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0) {
                    continue;
                }
                up[i][j]=i-1<0?1:up[i-1][j]+1;
                left[i][j]=j-1<0?1:left[i][j-1]+1;
                if(up[i][j]==1||left[i][j]==1) {
                    max=Math.max(max,1);
                    continue;
                }
                int len=Math.min(up[i][j],left[i][j]);
                for(int l=len;l>1;l--) {
                    int index=l-1;
                    if(up[i][j-index]>=l && left[i-index][j]>=l) {
                        max=Math.max(max,l*l);
                        break;
                    }
                }
            }
        }
        return max;
    }
}
