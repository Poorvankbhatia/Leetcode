/*

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right
, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination.
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination
(included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of
the maze are all walls. The start and destination coordinates are represented by row and column indexes.


Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.


Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

 */
package bfsdfs.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank.b on 20/10/17.
 */
public class Maze2 {

    int[] xMove = new int[]{0,0,1,-1};
    int[] yMove = new int[]{1,-1,0,0};

    private class Node {
        int x,y,distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] dest) {

        if(maze==null || maze.length==0) {
            return 0;
        }

        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];

        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(start[0],start[1],0);
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if(current.x==dest[0] && current.y==dest[1]) {
                return current.distance;
            }
            for (int i=0;i<4;i++) {
                int nextX = current.x+xMove[i];
                int nextY = current.y+yMove[i];
                int dist=0;
                while (nextX>=0 && nextY>=0 && nextX<m && nextY<n && maze[nextX][nextY]==0) {
                    nextX += xMove[i];
                    nextY += yMove[i];
                    dist++;
                }
                int lastVisitedX=nextX-xMove[i],lastVisitedY=nextY-yMove[i];
                if(!visited[lastVisitedX][lastVisitedY]) {
                    visited[lastVisitedX][lastVisitedY]=true;
                    queue.add(new Node(lastVisitedX,lastVisitedY,dist+current.distance));
                }
            }
        }

        return -1;

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
        int[] end = new int[]{4,2};
        System.out.println(new Maze2().shortestDistance(arr,start,end));
    }

}

/*

Time complexity : O(m*n*max(m,n)). Time complexity : O(m*n*{max}(m,n)). Complete traversal of maze will be done in the worst case.
Here, m and n refers to the number of rows and columns of the maze.
Further, for every current node chosen, we can travel upto a maximum depth of max(m,n) in any direction.

Space complexity : O(mn) queue size can grow upto m*n in the worst case.

For Dijisktra:

https://leetcode.com/articles/the-maze-ii/#approach-3-using-dijkstra-algorithm-accepted
 */