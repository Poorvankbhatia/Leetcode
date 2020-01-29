/*

In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:

|x| + |y| <= 300

 */
package bfsdfs.medium;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class KnightMoves {

    public int minKnightMoves(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int level = 0;
        int[] dr = new int[]{-1, -1, 1, 1, -2, -2, 2, 2};
        int[] dc = new int[]{-2, 2, -2, 2, -1, 1, -1, 1};
        HashSet<Integer> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] curr = queue.remove();
                if(curr[0] == x && curr[1] == y) {
                    return level;
                }
                int hash = 1001 * curr[0] + curr[1]; //hash is an integer not a string like "x#y"
                if(!visited.contains(hash)) {
                    for(int i=0; i<dr.length; i++) {
                        int r = curr[0] + dr[i];
                        int c = curr[1] + dc[i];
                        if(Math.abs(r) + Math.abs(c) > 300) {  // constraint is |x| + |y| <= 300, pruning based on the constraint
                            continue;
                        }
                        queue.add(new int[]{r, c});
                    }
                    visited.add(hash);
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new KnightMoves().minKnightMoves(5,5));
    }

}

/*

O(1) Formula.

The crux of the problem is to notice the patterns that emerge when you draw the board.
The minimum number of moves needed to reach any square

5 4 5 4 5 4 5 6
4 3 4 3 4 5 4
3 4 3 4 3 4
2 3 2 3 4
3 2 3 2
2 1 4
2 3
0

Best part is solution is symmetrical across the axes and the diagonals.

public int minKnightMoves(int x, int y) {
        // Symmetry for axes
        x = Math.abs(x);
        y = Math.abs(y);
        // Symmetry for diagonal
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 1 && y == 0) {
            return 3;
        }
        if (x == 2 && y == 2) {
            return 4;
        }
        int delta = x - y;
        if (y > delta) {
            return (int) (delta - 2 * Math.floor((float) (delta - y) / 3));
        } else {
            return (int) (delta - 2 * Math.floor((delta - y) / 4));
        }
    }

Refer : https://stackoverflow.com/questions/2339101/knights-shortest-path-on-chessboard/8778592#8778592



 */