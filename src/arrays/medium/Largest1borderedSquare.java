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
        if (grid.length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] horizontal = new int[m+1][n+1];
        int[][] vertical = new int[m+1][n+1];
        int max=0;
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (grid[r - 1][c - 1] == 0) {
                    continue;
                }
                horizontal[r][c] = horizontal[r][c-1] + 1;
                vertical[r][c] = vertical[r-1][c] + 1;
                int small = Math.min(horizontal[r][c], vertical[r][c]);
                while (small>max){
                    if (vertical[r][c - small + 1] >= small && horizontal[r - small + 1][c] >= small) {
                        max = Math.max(max, small);
                        break;
                    }
                    small--;
                }
            }
        }
        return max*max;
    }
}

/*
Create auxillary horizontal and vertical arrays first
Look Images.
Then starting from bottom right,for every i,j ; we find small=min (ver[i][j], hor[i][j]) (marked in orange) ,
then look at all distances in [1,small] vertically in hor array and horizontally in ver array. If values(shown in blue)
are greater than small and if small is greater than curr result, then we update result.


consider the below matrix template,

x------------y
|            |
|            |
|            |
z----------- w
So when you are starting the for-loop you are at bottom right position or (row-1,col-1) of original matrix or 'w' in the above format.
You look at horizontal and ver matrix to see which is the minimum consecutive ones you can make from that position.(minimum because the sides
have to be equal, if you take maximum of the two you may not have enough 1s on one side to make an equal side.)
Now the minimum value you got shows the highest length you can make from the side 'z-w' and 'y-w' edges. Now you have to make sure that two other
edges 'x-z' and 'x-y' exists in the matrix. This is why we have the loop

if (ver[i][j-small+1] >= small && hor[i-small+1][j] >= small) max = small; small--;
which necessarily searches for a possible z and y to make 'x-z' and 'x-y' sides such that they have atleast the minimum length you claim to have made
from 'z-w' and 'y-w' edges so that at last we get a square which all have equal sides. So in short you are first making z-w and y-w sides and then
searching whether 'x-z' and 'x-y' sides with atleast z-w and y-w side length exists or not to make that particular square.
small--; here means that if the particular length is not possible which is the maximum length we can make from 'w' then we will go for a shorter length
and try for it which would still be possible since it is less than maximum possible valid square length.

 */