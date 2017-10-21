/*

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls.
The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right


Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

 */
package bfsdfs.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank.b on 20/10/17.
 */
public class Maze1 {

    int[] xMove = new int[]{0,0,1,-1};
    int[] yMove = new int[]{1,-1,0,0};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze==null || maze.length==0){
            return false;
        }
        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        queue.add(start);
        visited[start[0]][start[1]]=true;
        while (!queue.isEmpty()) {
            int[] element = queue.remove();
            if(element[0]==destination[0] && element[1]==destination[1]) {
                return true;
            }
            for (int i=0;i<4;i++) {
                int nextX = element[0] + xMove[i];
                int nextY = element[1] + yMove[i];
                while (nextX>=0 && nextY>=0 && nextX<m  && nextY<n && maze[nextX][nextY]==0) {
                    /*
                      Don't add visited check here , because we need to continue until we hit an obstacle and not an
                      intermediate visited node.
                     */
                    nextX += xMove[i];
                    nextY += yMove[i];
                }
                int lastVisitedX = nextX-xMove[i],lastVisitedY = nextY-yMove[i];
                if(!visited[lastVisitedX][lastVisitedY]) {
                    queue.add(new int[]{lastVisitedX,lastVisitedY});
                    visited[lastVisitedX][lastVisitedY]=true;
                }
            }
        }

        return false;

    }

    public static void main(String[] args) {

        int[][] arr = new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };

        int[] start = new int[]{0,4};
        int[] end = new int[]{1,1}; // It will keep moving until it hits an obstacle.
        System.out.println(new Maze1().hasPath(arr,start,end));
    }



}

/*

Time complexity : O(mn). Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze.

Space complexity : O(mn). visited array of size m*n is used and queue size can grow upto m*n in worst case.

 */