/*

In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1).
The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).

In one move the snake can:

Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).


Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty.
In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).


Return the minimum number of moves to reach the target.

If there is no way to reach the target, return -1.


Input: grid = [[0,0,0,0,0,1],
               [1,1,0,0,1,0],
               [0,0,0,0,1,1],
               [0,0,1,0,1,0],
               [0,1,1,0,0,0],
               [0,1,1,0,0,0]]
Output: 11
Explanation:
One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
Example 2:

Input: grid = [[0,0,1,1,1,1],
               [0,0,0,0,1,1],
               [1,1,0,0,0,1],
               [1,1,1,0,0,1],
               [1,1,1,0,0,1],
               [1,1,1,0,0,0]]
Output: 9


Constraints:

2 <= n <= 100
0 <= grid[i][j] <= 1
It is guaranteed that the snake starts at empty cells.


 */
package bfsdfs.hard;

import java.util.*;

public class MinimumMovesToReachTargetWithRotation {


    private class SnakeCell {
        int[] start;
        int[] end;
        String val;

        public SnakeCell(int[] start, int[] end) {
            this.start = start;
            this.end = end;
            val = start[0]+"_"+start[1]+"_"+end[0]+"_"+end[1];
        }
    }

    public int minimumMoves(int[][] grid) {

        if(grid==null || grid.length==0) {
            return -1;
        }

        int n = grid.length;
        SnakeCell snakeCell = new SnakeCell(new int[]{0,0},new int[]{0,1});
        Queue<SnakeCell> queue = new LinkedList<>();
        queue.offer(snakeCell);
        Set<String> visited = new HashSet<>();
        visited.add(snakeCell.val);

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size>0) {
                SnakeCell pop = queue.poll();
                List<SnakeCell> list = getNextList(pop,n,grid);
                for (SnakeCell cell : list) {
                    int[] start = cell.start;
                    int[] end = cell.end;
                    if(start[0]==n-1 && start[1]==n-2 && end[0]==n-1 && end[1]==n-1) {
                        return ans+1;
                    }
                    if(isValid(cell,n,grid,visited)) {
                        visited.add(cell.val);
                        queue.offer(cell);
                    }
                }
                size--;
            }
            ans++;
        }

        return -1;

    }

    private boolean isValid(SnakeCell snakeCell,int n,int[][] grid,Set<String> visited) {
        int[] start = snakeCell.start;
        int[] end = snakeCell.end;
        return validCoordinates(start,n) && validCoordinates(end,n) && grid[start[0]][start[1]] != 1 && grid[end[0]][end[1]] != 1 && !visited.contains(snakeCell.val);

    }

    private boolean validCoordinates(int[] start,int n) {
        return start[0] >= 0 && start[1] < n && start[0] < n && start[1] >= 0;
    }

    private List<SnakeCell> getNextList(SnakeCell snakeCell,int n,int[][] grid) {
        int[] start = snakeCell.start;
        int[] end = snakeCell.end;

        List<SnakeCell> snakeCells = new ArrayList<>();
        // (1,2) , (1,3)
        if(start[0] == end[0]){
            SnakeCell right = new SnakeCell(end,new int[]{end[0],end[1]+1}); // (1,3) , (1,4)
            SnakeCell down = new SnakeCell(new int[]{start[0]+1,start[1]},new int[]{end[0]+1,end[1]}); // (2,2) , (2,3)
            SnakeCell clockwise = new SnakeCell(start,new int[]{end[0]+1,end[1]-1}); // (1,2) , (2,2)
            if(start[0]+1 < n && start[1]+1 <n && grid[start[0]+1][start[1]+1]==0) { // two cells under should be empty
                snakeCells.add(clockwise);
            }
            snakeCells.add(right);
            snakeCells.add(down);
        }
        // (1,2) , (2,2)
        if(start[1]==end[1]) {
            SnakeCell right = new SnakeCell(new int[]{start[0],start[1]+1},new int[]{end[0],end[1]+1}); // (1,3) , (2,3)
            SnakeCell down = new SnakeCell(end,new int[]{end[0]+1,end[1]}); // (2,2) , (3,2)
            SnakeCell counterClockwise = new SnakeCell(start,new int[]{end[0]-1,end[1]+1}); // (1,2), (1,3)
            if(start[0]+1 < n && start[1]+1 <n && grid[start[0]+1][start[1]+1]==0) { // two cells to its right should be empty.
                snakeCells.add(counterClockwise);
            }
            snakeCells.add(right);
            snakeCells.add(down);
        }
        return snakeCells;

    }


    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0,0,1,0},
                {0,1,1,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        System.out.println(new MinimumMovesToReachTargetWithRotation().minimumMoves(grid));
    }


}
