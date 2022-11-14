/*

We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys,
and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk
outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.
This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys
and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.



Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6


Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.

 */
package bfsdfs.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by poorvank.b on 12/07/18.
 */
public class ShortestPathToGetAllKeys {

    /*

    Use Bit to represent the keys.
    Use State to represent visited states.

     */
    class State {
        int keys, i, j;
        State(int keys, int i, int j) {
            this.keys = keys;
            this.i = i;
            this.j = j;
        }
    }
    public int shortestPathAllKeys(String[] grid) {
        int x = -1, y = -1, m = grid.length, n = grid[0].length(), max = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    x = i;
                    y = j;
                }
                if (c >= 'a' && c <= 'f') {
                    max = Math.max(c - 'a' + 1, max);
                }
            }
        }
        State start = new State(0, x, y);
        Queue<State> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + x + " " + y);
        q.offer(start);
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                State cur = q.poll();
                if (cur.keys == (1 << max) - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int i = cur.i + dir[0];
                    int j = cur.j + dir[1];
                    int keys = cur.keys;
                    if (i >= 0 && i < m && j >= 0 && j < n) {
                        char c = grid[i].charAt(j);
                        if (c == '#') {
                            continue;
                        }
                        //if we have to find keys abc and now we have found ab, state is 110(2) = 6; If we have found ac, state is 101(2) = 5.
                        if (c >= 'a' && c <= 'f') {
                            keys = keys | 1 << (c - 'a'); // Same concept as Sum of all paths BT
                        }
                        if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) {
                            continue;
                        }
                        if (!visited.contains(keys + " " + i + " " + j)) {
                            visited.add(keys + " " + i + " " + j);
                            q.offer(new State(keys, i, j));
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] s = new String[]{"@...a",
                                  ".###A",
                                  "b.BCc"};
        System.out.println(new ShortestPathToGetAllKeys().shortestPathAllKeys(s));
    }

}

/*

Complexity : which is 2^k * m * n


Using string builder:

class Solution {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final String EMPTY_KEY_MASK = "      ";
    private static final String FULL_KEY_MASK = "abcdef";

    public int shortestPathAllKeys(String[] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length();

        int startRow = -1;
        int startCol = -1;
        int numKeys = 0;
        for(int r=0; r<numRows; r++) {
            for(int c=0; c<numCols; c++) {
                char ch = grid[r].charAt(c);

                if('@' == ch) {
                    startRow = r;
                    startCol = c;
                } else if(Character.isLowerCase(ch)) {
                    numKeys = Math.max(numKeys, ch - 'a' + 1);
                }
            }
        }

        String allKeys = FULL_KEY_MASK.substring(0, numKeys);
        int numSteps = 0;

        Set<String> visited = new HashSet<>();
        Deque<State> queue = new ArrayDeque<>();
        State startState = new State(new StringBuilder(EMPTY_KEY_MASK.substring(0, numKeys)),
                                     startRow, startCol);
        queue.offer(startState);
        visited.add(startState.toString());

        while(!queue.isEmpty()) {
            int qs = queue.size();
            for(int i=0; i<qs; i++) {
                State s = queue.poll();
                if(allKeys.equals(s.keySb.toString())) {
                    return numSteps;
                }

                for(int[] d : DIRECTIONS) {
                    int newRow = s.row + d[0];
                    int newCol = s.col + d[1];

                    if(newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols &&
                        grid[newRow].charAt(newCol) != '#') {
                        char c = grid[newRow].charAt(newCol);
                        if(Character.isUpperCase(c) && !s.canUnlock(c)) {
                            // Can't unlock this door
                            continue;
                        }

                        StringBuilder stateKeys = new StringBuilder(s.keySb);
                        if(Character.isLowerCase(c)) {
                            // Pick up this key
                            stateKeys.setCharAt(c - 'a', c);
                        }

                        State newState = new State(stateKeys, newRow, newCol);
                        if(!visited.contains(newState.toString())) {
                            visited.add(newState.toString());
                            queue.offer(newState);
                        }
                    }
                }
            }

            numSteps++;
        }

        return -1;
    }


    private class State {
        private StringBuilder keySb = null;
        private int row = -1;
        private int col = -1;

        private State(StringBuilder keySb, int aRow, int aCol) {
            this.keySb = keySb;
            this.row = aRow;
            this.col = aCol;
        }

        private boolean canUnlock(char c) {
            c = Character.toLowerCase(c);
            return keySb.charAt(c - 'a') == c;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(keySb);
            sb.append(",");
            sb.append(row);
            sb.append(",");
            sb.append(col);

            return sb.toString();
        }
    }
}

 */