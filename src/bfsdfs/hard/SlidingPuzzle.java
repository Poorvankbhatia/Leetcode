/*

On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved.
If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

 */
package bfsdfs.hard;

import java.util.*;

/**
 * Created by poorvank.b on 28/01/18.
 */
public class SlidingPuzzle {

    private class Node {
        private String code;
        private int x;
        private int y;
        private int[][] board;
        public Node(String code, int x, int y,int[][] board) {
            this.code = code;
            this.x = x;
            this.y = y;
            this.board = board;
        }
    }

    private int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int slidingPuzzle(int[][] board) {

        if(board==null || board.length==0) {
            return -1;
        }
        int startX=0;
        int startY=0;

        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if(board[i][j]==0) {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }
        Queue<Node> queue = new LinkedList<>();

        String current = getBoardString(board);
        queue.add(new Node(current,startX,startY,board));

        Set<String> set = new HashSet<>();
        set.add(current);

        String target = "123450";
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                Node pop = queue.remove();
                if(pop.code.equals(target)) {
                    return step;
                }
                int[][] popBoard = pop.board;
                for (int[] dir : directions) {
                    int nextX = dir[0]+pop.x;
                    int nextY = dir[1]+pop.y;
                    if(isSafe(nextX,nextY)) {
                        int[][] newBoard = new int[2][3];
                        int t = 0;
                        for (int[] row: popBoard)
                            newBoard[t++] = row.clone();
                        newBoard[pop.x][pop.y]=newBoard[nextX][nextY];
                        newBoard[nextX][nextY]=0;
                        String newBoardString = getBoardString(newBoard);
                        if(set.add(newBoardString)) {
                            queue.add(new Node(newBoardString,nextX,nextY,newBoard));
                        }
                    }
                }
            }
            step++;

        }


        return -1;

    }

    private boolean isSafe(int i,int j) {
        return i>=0 && i<2 && j>=0 && j<3;
    }

    private String getBoardString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(aBoard[j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {4,1,2},
                {5,0,3}
        };

        System.out.println(new SlidingPuzzle().slidingPuzzle(arr));

    }

}
/*

Simple BFS.

 * Time Complexity: O(V + E).
 * V = Number of possible states = (M*N)!.
 * E = 3 * V, because there can be a maximum of 3 edges from each vertex.
 * Total TC = O((MN)! + 3*(MN)!) = O((MN)!) = O(4 * 6!)
 *
 * Space Complexity: O((MN)!) to save each state in begin, end and visited
 * collections = O(6!)
 *
 * M = Number of rows. N = Number of cols.

 DFS:

 class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    Map<Integer, Integer> map = new HashMap<>();
    int min_move = Integer.MAX_VALUE;
    public int slidingPuzzle(int[][] board) {
        map.put(123450, 0);
        int[] zero = new int[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    zero[0] = i;
                    zero[1] = j;
                    break;
                }
            }
        }
        helper(board, zero[0], zero[1], 0);
        return min_move == Integer.MAX_VALUE ? -1 : min_move;
    }
    private void helper(int[][] board, int x, int y, int move) {
        if (move > min_move) return;
        int code = encode(board);
        if (code == 123450) {
            min_move = move;
            return;
        }
        if (map.containsKey(code)) {
            if (move > map.get(code)) return;
        }
        map.put(code, move);
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < 2 && ny >= 0 && ny < 3) {
                swap(board, x, y, nx, ny);
                helper(board, nx, ny, move + 1);
                swap(board, nx, ny, x, y);
            }
        }
    }
    private void swap (int[][] board, int i, int j, int ii, int jj) {
        int temp = board[i][j];
        board[i][j] = board[ii][jj];
        board[ii][jj] = temp;
    }
    private int encode(int[][] board) {
        int code = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                code *= 10;
                code += board[i][j];
            }
        }
        return code;
    }
}

********

Better sol:

public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }
        HashSet<String> visited = new HashSet<>();
        // all the positions 0 can be swapped to
        int[][] dirs = new int[][] { { 1, 3 }, { 0, 2, 4 },
                { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int res = 0;
        while (!queue.isEmpty()) {
            // level count, has to use size control here, otherwise not needed
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return res;
                }
                int zero = cur.indexOf('0');
                // swap if possible
                for (int dir : dirs[zero]) {
                    String next = swap(cur, zero, dir);
                    if (visited.contains(next)) {
                        continue;
                    }
                    visited.add(next);
                    queue.offer(next);

                }
            }
            res++;
        }
        return -1;
    }

    private String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }


 0 1 2
3 4 5 --> 0 can go to {1, 3}

1 0 2
3 4 5 --> 0 can go to index of {0,2,4}

1 2 0
3 4 5 --> 0 can go to {1, 5}

1 2 3
0 4 5 --> 0 can go to {0,4}

1 2 3
4 0 5 --> 0 can go to {1, 3, 5}

1 2 3
4 5 0 --> 0 can go to {2, 4}

 */
