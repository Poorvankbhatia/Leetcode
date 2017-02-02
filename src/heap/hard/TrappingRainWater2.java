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

        MinPriorityQueue<Cell> minPriorityQueue = new MinPriorityQueue<>();
        int rows = heightMap.length;
        int cols = heightMap[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i=0;i<rows;i++) {
            visited[i][0] = true;
            visited[i][cols-1] = true;
            minPriorityQueue.insert(new Cell(i,0,heightMap[i][0]));
            minPriorityQueue.insert(new Cell(i,cols-1,heightMap[i][cols-1]));
        }


        for (int i=1;i<cols-1;i++) {
            visited[0][i] = true;
            visited[rows-1][i] = true;
            minPriorityQueue.insert(new Cell(0,i,heightMap[0][i]));
            minPriorityQueue.insert(new Cell(rows-1,i,heightMap[rows-1][i]));
        }


        int[] xs = {0,  0, 1, -1};
        int[] ys = {1, -1, 0,  0};
        int sum = 0;
        while (!minPriorityQueue.isEmpty()) {
            Cell cell = minPriorityQueue.deleteMin();
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + xs[i];
                int ny = cell.y + ys[i];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sum += Math.max(0, cell.height - heightMap[nx][ny]);
                    minPriorityQueue.insert(new Cell(nx, ny, Math.max(heightMap[nx][ny], cell.height)));
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