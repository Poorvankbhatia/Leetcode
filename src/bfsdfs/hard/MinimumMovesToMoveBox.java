/*
A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.

Your task is to move the box 'B' to the target position 'T' under the following rules:

The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
The character '.' represents the floor which means a free cell to walk.
The character '#' represents the wall which means an obstacle (impossible to walk there).
There is only one box 'B' and one target cell 'T' in the grid.
The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
The player cannot walk through the box.
Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.

 Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#",".","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 3
Explanation: We return only the number of times the box is pushed.
Example 2:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#","#","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: -1
Example 3:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T",".",".","#","#"],
               ["#",".","#","B",".","#"],
               ["#",".",".",".",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 5
Explanation: push the box down, left, left, up and up.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid contains only characters '.', '#', 'S', 'T', or 'B'.
There is only one character 'S', 'B', and 'T' in the grid.
 */
package bfsdfs.hard;

import java.util.*;

public class MinimumMovesToMoveBox {

    private final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] player = null, box = null, target = null;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') player = new int[]{i, j};
                else if (grid[i][j] == 'B') box = new int[]{i, j};
                else if (grid[i][j] == 'T') target = new int[]{i, j};
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<List<Integer>> seen = new HashSet<>();

        queue.offer(new int[]{box[0], box[1], player[0], player[1]});
        seen.add(new ArrayList<>(Arrays.asList(box[0], box[1], player[0], player[1])));
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int[] e = queue.poll();
                box = new int[]{e[0], e[1]};
                player = new int[]{e[2], e[3]};
                if (Arrays.equals(box, target)) return ans;
                for (int[] dir : DIRS) {
                    int x = box[0] + dir[0], y = box[1] + dir[1];
                    if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] != '#' &&
                            !seen.contains(new ArrayList<>(Arrays.asList(x, y, box[0], box[1]))) &&
                            canReach(grid, m, n, player, box, new int[]{box[0] - dir[0], box[1] - dir[1]})) {
                        seen.add(new ArrayList<>(Arrays.asList(x, y, box[0], box[1])));
                        queue.offer(new int[]{x, y, box[0], box[1]});
                    }
                }
            }
            ans++;
        }

        return -1;
    }

    // canReach returns true if the player can move to des.
    private boolean canReach(char[][] grid, int m, int n, int[] player, int[] box, int[] des) {
        Queue<int[]> queue = new ArrayDeque<>();
        Set<List<Integer>> seen = new HashSet<>();

        queue.offer(player);
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (Arrays.equals(p, des)) return true;
            for (int[] dir : DIRS) {
                int x = p[0] + dir[0], y = p[1] + dir[1];
                if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] != '#' &&
                        !Arrays.equals(new int[]{x, y}, box) && !seen.contains(new ArrayList<>(Arrays.asList(x, y)))) {
                    seen.add(new ArrayList<>(Arrays.asList(x, y)));
                    queue.offer(new int[]{x, y});
                }
            }
        }

        return false;
    }

}

/*

Let's break the question into simple parts:

Let's think that we have no person, and we have to find the minimum path between box and the target.
Easy right? Simple BFS.

If you know how to solve the first part, what I actually do is modify first part with few constraints.

Just check whether the box can be shifted to the new position(up, down, left, right)

For it to be shifted to the new position the person has to be in a corresponding position right?
So we check if the person can travel from his old position to his corresponding new position(using another BFS).
If the person can travel to his new position than the box can be shifted, otherwise the box cannot be shifted.

We keep repeating step 2 until we reach the target or it is not possible to move the box anymore.

 */