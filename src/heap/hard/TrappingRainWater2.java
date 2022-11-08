/*

Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

 */
package heap.hard;

import heap.MinPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by poorvank on 02/02/17.
 */
public class TrappingRainWater2 {

    private class Cell implements Comparable<Cell> {
        int x;
        int y;
        int height;

        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        public int compareTo(Cell cell) {
            return this.height-cell.height;
        }
    }

    public int trapRainWater(int[][] heightMap) {

        if(heightMap==null || heightMap.length==0) {
            return 0;
        }

        PriorityQueue<Cell> minPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        int rows = heightMap.length;
        int cols = heightMap[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i=0;i<rows;i++) {
            visited[i][0] = true;
            visited[i][cols-1] = true;
            minPriorityQueue.add(new Cell(i,0,heightMap[i][0]));
            minPriorityQueue.add(new Cell(i,cols-1,heightMap[i][cols-1]));
        }


        for (int i=1;i<cols-1;i++) {
            visited[0][i] = true;
            visited[rows-1][i] = true;
            minPriorityQueue.add(new Cell(0,i,heightMap[0][i]));
            minPriorityQueue.add(new Cell(rows-1,i,heightMap[rows-1][i]));
        }


        int[] xs = {0,  0, 1, -1};
        int[] ys = {1, -1, 0,  0};
        int sum = 0;
        while (!minPriorityQueue.isEmpty()) {
            Cell cell = minPriorityQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + xs[i];
                int ny = cell.y + ys[i];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sum += Math.max(0, cell.height - heightMap[nx][ny]);
                    minPriorityQueue.add(new Cell(nx, ny, Math.max(heightMap[nx][ny], cell.height)));
                }
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        /*
        After you process 8, the downward 1 will be replaced as 8, instead of 1 as height.

         */
        int[][] arr = new int[][]{
        {9,9,9,9,9,9,8,9,9,9,9},
        {9,0,0,0,0,0,1,0,0,0,9},
        {9,0,0,0,0,0,0,0,0,0,9},
        {9,0,0,0,0,0,0,0,0,0,9},
        {9,9,9,9,9,9,9,9,9,9,9}
        };

        System.out.print(new TrappingRainWater2().trapRainWater(arr));
    }

}

/*

What determines the amount of water can a bar can hold? It is the min height of the max heights along all paths to the boundary
(not just 4 direction!!!, which was my first intuition) Look at the example below. If we add 2 units of water into the 1 in the center, it will overflow to 0.
0 0 3 0 0
0 0 2 0 0
3 2 1 2 3
0 0 2 0 0
0 0 3 0 0

Just like 1-D two pointer approach, we need to find some boundary.
Because all boundary cells cannot hold any water for sure, we use them as the initial boundary naturally.

Then which bar to start? Find the min bar (let's call it A) on the boundary (heap is a natural choice), then do 1 BFS (4 directions). Why BFS?
Because we are sure that the amount of water that A's neighbors can hold is only determined by A now for the same reason in 1D two-pointer approach.

How to update the heap during BFS
Step 1. Remove the min bar A from the heap
Step 2. If A's neighbor B's height is higher, it cannot hold any water. Add it to the heap
Step 3. If B's height is lower, it can hold water and the amount of water should be height_A - height_B. Here comes the tricky part,
we still add B's coordinate into the heap, BUT change its height to A's height because A is the max value along this path
(for this reason we cannot just use heightMap and need a class/array to store it's coordinates and UPDATED height).
And we can think of B as a replacement of A now and never worry about A again. Therefore a new boundary is formed and we can repeat this process again.

Use PriorityQueue, sort lowest on top, because the lowest surroundings determines the best we can get.
Bukkit theory: the lowest bar determines the height of the bukkit water. So, we always process the lowest first.
Therefore, we use a min-heap, a natural order priorityqueue based on height.
Note: when adding a new block into the queue, comparing with the checked origin, we still want to add the higher height into queue.
(The high bar will always exist and hold the bukkit.)

During the while loop, every cell is put into and take out of the heap at most once,
and we are doing so in a BFS style, meaning that there is m + n elements in the heap at the worst case.
So it is O(m * n * log(m + n)) in the worst case.
So the run time complexity would be O(m * n * log(m + n)).
The space complexity is obviously O(m * n) because of the visited array.

 */